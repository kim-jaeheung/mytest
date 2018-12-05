package or.kosta.mvc.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.print.attribute.standard.PagesPerMinute;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import or.kosta.dto.PageVO;
import or.kosta.dto.SearchVO;
import or.kosta.dto.ShowVO;
import or.kosta.mvc.dao.ShowDao;

@Controller
public class UpSaveController {
	
	private static final int BUFFER_SIZE = 4096;

	
	@Autowired
	private ShowDao dao;
	
	@GetMapping("/showform")
	public String upform() {
		return "showform";
	}
	
	@RequestMapping(value="/upsave2", method=RequestMethod.POST)
	public ModelAndView save2(@ModelAttribute("svo") ShowVO vo,
			HttpServletRequest request) {
		String imgpath = "resources\\imgfile";
		String r_path = request.getRealPath("/");
		String oriFn = vo.getMultipartFile().getOriginalFilename();
		StringBuffer path = new StringBuffer();
		path.append(r_path).append(imgpath).append("\\");
		path.append(oriFn);
		System.out.println(path);
		File f = new File(path.toString());
		try {
			vo.getMultipartFile().transferTo(f);
		} catch (Exception e) {
			e.printStackTrace();
		}
		vo.setPath(vo.getMultipartFile().getOriginalFilename());
		dao.saveShow(vo);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/showlist?page=1");
		
		return mav;
	}
	
	@RequestMapping(value="/showlist", method=RequestMethod.GET)
	public ModelAndView list(int page, String searchType, String searchValue) {
		PageVO pageInfo = new PageVO();
		int rowsPerPage = 5;
		int pagesPerBlock = 5;
		int currentPage = page;
		int currentBlock = 0;
		if(currentPage % pagesPerBlock == 0) {
			currentBlock = currentPage / pagesPerBlock;
		}else {
			currentBlock = currentPage / pagesPerBlock + 1;
		}
		int startRow = (currentPage - 1) * rowsPerPage + 1;
		int endRow = currentPage * rowsPerPage;
		SearchVO svo = new SearchVO();
		svo.setBegin(String.valueOf(startRow));
		svo.setEnd(String.valueOf(endRow));
		svo.setSearchType(String.valueOf(searchType));
		svo.setSearchValue(String.valueOf(searchValue));
		
		// 전체 레코드 수
		int totalRows = dao.getTotalCount(svo);
		System.out.println("totalRows : "+totalRows);
		int totalPages = 0;
		
		// 전체 페이지를 구하는 공식
		if(totalRows % rowsPerPage == 0) {
			totalPages = totalRows / rowsPerPage;
		}else {
			totalPages = totalRows / rowsPerPage + 1;
		}
		
		//전체 블록 구하는 공식
		int totalBlocks = 0;
		if(totalPages % pagesPerBlock == 0) {
			totalBlocks = totalPages / pagesPerBlock;
		}else {
			totalBlocks = totalPages / pagesPerBlock + 1;
		}
		
		// PageVO에 setter로 값을 주입.
		pageInfo.setCurrentPage(currentPage);
		pageInfo.setCurrentBlock(currentBlock);
		pageInfo.setRowsPerPage(rowsPerPage);
		pageInfo.setPagesPerBlock(pagesPerBlock);
		pageInfo.setStartRow(startRow);
		pageInfo.setEndRow(endRow);
		pageInfo.setTotalRows(totalRows);
		pageInfo.setTotalPages(totalPages);
		pageInfo.setTotalBlocks(totalBlocks);		
		
		//----------------------------------------------
		ModelAndView mav = new ModelAndView();
		mav.setViewName("showlist");
		// Map을 Dao에 주입
		List<ShowVO> list = dao.getList(svo);
		
		//페이지와 리스트 값을 ModelAndView로 사용해서 값 전달
		mav.addObject("pageInfo",pageInfo);
		mav.addObject("searchType",searchType);
		mav.addObject("searchValue",searchValue);
		mav.addObject("list",list);
		
		return mav;
	}	
	
	// filedown 
	
	@RequestMapping(value="/fileDown")
	public void fileDown(@RequestParam("fileName") String fileName,
			HttpSession session, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// applicationContext 객체를 request로 부터 얻어냄
		ServletContext context = request.getServletContext();
		//업로드된 경로 가져옴
		String path = session.getServletContext().getRealPath("/resources/imgfile/"+fileName);  
		
		//그 경로로 파일 객체를 생성
		File file = new File(path);
		//FileInputStream 스트림으로 읽어옴
		FileInputStream fi = new FileInputStream(file);
		//요청 객체로부터 연결될 브라우저의 마임타입을 얻어냄
		String mimeType = context.getMimeType(path);
		//만약 마임값이 없으면 그냥 디폴트 값으로 연결
		if(mimeType == null) {
			mimeType = "application/octet-stream";
		}
		//지정된 마임 타입 세팅
		response.setContentType(mimeType);
		// 다운로드될 파일의 길이 세팅
		response.setContentLength((int) file.length());
		//다운로드 Type을 설정
		String headerKey = "Content-Disposition";
		String headerValue=String.format("attachment; filename=\"%s\"", file.getName());
		//위의 다운로드 타입의 정보를 헤더로 생성
		response.setHeader(headerKey, headerValue);
		// 브라우저로부터 스트림을 연결
		OutputStream outstream = response.getOutputStream();
		//버퍼를 끼워서 빠르게 전달 목적
		byte[] buffer = new byte[BUFFER_SIZE];
		// 이제 브라우저로 보내고, 자원 닫으면 끝
		int bytesRead = -1;
		while((bytesRead = fi.read(buffer)) != -1) {
			outstream.write(buffer,0,bytesRead);
		}
		fi.close();
		outstream.close();
	}
	
	@RequestMapping(value="downloadExcel")
	public ModelAndView downloadExcel() {
		List<ShowVO> listBooks = dao.getList2();
		// 뷰 이름, 모델 이름, 모델 값 주소
		return new ModelAndView("excelView", "listBooks", listBooks);
	}
	
	

}
