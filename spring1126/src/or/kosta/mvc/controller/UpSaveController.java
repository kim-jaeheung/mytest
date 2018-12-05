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
		
		// ��ü ���ڵ� ��
		int totalRows = dao.getTotalCount(svo);
		System.out.println("totalRows : "+totalRows);
		int totalPages = 0;
		
		// ��ü �������� ���ϴ� ����
		if(totalRows % rowsPerPage == 0) {
			totalPages = totalRows / rowsPerPage;
		}else {
			totalPages = totalRows / rowsPerPage + 1;
		}
		
		//��ü ��� ���ϴ� ����
		int totalBlocks = 0;
		if(totalPages % pagesPerBlock == 0) {
			totalBlocks = totalPages / pagesPerBlock;
		}else {
			totalBlocks = totalPages / pagesPerBlock + 1;
		}
		
		// PageVO�� setter�� ���� ����.
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
		// Map�� Dao�� ����
		List<ShowVO> list = dao.getList(svo);
		
		//�������� ����Ʈ ���� ModelAndView�� ����ؼ� �� ����
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
		// applicationContext ��ü�� request�� ���� ��
		ServletContext context = request.getServletContext();
		//���ε�� ��� ������
		String path = session.getServletContext().getRealPath("/resources/imgfile/"+fileName);  
		
		//�� ��η� ���� ��ü�� ����
		File file = new File(path);
		//FileInputStream ��Ʈ������ �о��
		FileInputStream fi = new FileInputStream(file);
		//��û ��ü�κ��� ����� �������� ����Ÿ���� ��
		String mimeType = context.getMimeType(path);
		//���� ���Ӱ��� ������ �׳� ����Ʈ ������ ����
		if(mimeType == null) {
			mimeType = "application/octet-stream";
		}
		//������ ���� Ÿ�� ����
		response.setContentType(mimeType);
		// �ٿ�ε�� ������ ���� ����
		response.setContentLength((int) file.length());
		//�ٿ�ε� Type�� ����
		String headerKey = "Content-Disposition";
		String headerValue=String.format("attachment; filename=\"%s\"", file.getName());
		//���� �ٿ�ε� Ÿ���� ������ ����� ����
		response.setHeader(headerKey, headerValue);
		// �������κ��� ��Ʈ���� ����
		OutputStream outstream = response.getOutputStream();
		//���۸� ������ ������ ���� ����
		byte[] buffer = new byte[BUFFER_SIZE];
		// ���� �������� ������, �ڿ� ������ ��
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
		// �� �̸�, �� �̸�, �� �� �ּ�
		return new ModelAndView("excelView", "listBooks", listBooks);
	}
	
	

}
