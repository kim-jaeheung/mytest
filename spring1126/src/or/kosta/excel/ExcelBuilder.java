package or.kosta.excel;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import or.kosta.dto.ShowVO;

@SuppressWarnings("deprecation")
public class ExcelBuilder extends AbstractExcelView{
	// excelView 뷰를 찾으면, 이것을 현재 클래스 ExcelBuilder 가 AbstractExcelView를 상속 했기 때문에 이것은 뷰 이다.
	// DispatcherServlet -> [view]jsp (model) : forward
	@Override
	protected void buildExcelDocument(Map<String, Object> model, HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// HSSF : 마이크로 소프트의 엑셀 전용 클래스
		// DispatcherServlet 에 의해서 Model을 받는다.
		// ${listBooks}
		List<ShowVO> listBooks = (List<ShowVO>) model.get("listBooks");
		// poi 문법
		HSSFSheet sheet = workbook.createSheet("show Book");
		sheet.setDefaultColumnWidth(30);
		CellStyle style = workbook.createCellStyle();
		Font font = workbook.createFont();
		font.setFontName("Arial");
		style.setFillForegroundColor(HSSFColor.BLUE.index);
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		font.setColor(HSSFColor.WHITE.index);
		style.setFont(font);
		HSSFRow header = sheet.createRow(0);
		
		//엑셀의 셀 타이틀
		String[] str = {"num","code","image","writer","price","date"};
		for(int i =0; i<str.length; i++) {
			header.createCell(i).setCellValue(str[i]);
			header.getCell(i).setCellStyle(style);
		}
		int rowCount = 1;
		for(ShowVO book : listBooks) {
			HSSFRow aRow = sheet.createRow(rowCount++);
			aRow.createCell(0).setCellValue(book.getNum());
			aRow.createCell(1).setCellValue(book.getGrpcode());
			aRow.createCell(2).setCellValue(book.getPath());
			aRow.createCell(3).setCellValue(book.getWriter());
			aRow.createCell(4).setCellValue(book.getPrice());
			aRow.createCell(5).setCellValue(book.getRegdate());
		}
		//  응답객체로 부터 다운로드 받을 타입과 파일 이름을 설정한다.
		response.setContentType("Application/Msexcel");
		response.setHeader("Content-Disposition", "attachment; filenname=tboard_exce.xls"); 
	}

}
