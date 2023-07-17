package ks47team03.admin.utill;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import ks47team03.user.dto.Cup;

	@Component
	public class SpreadsheetFilePasing {

		public List<Cup> pasingToDiscardCupList (MultipartFile file){
			try {
				InputStream spreadsheetFile = file.getInputStream();
	            // 엑셀 파일로 Workbook instance를 생성한다.
	            XSSFWorkbook workbook = new XSSFWorkbook(spreadsheetFile);

	            // workbook의 첫번째 sheet를 가져온다.
	            XSSFSheet sheet = workbook.getSheetAt(0);

	            // 만약 특정 이름의 시트를 찾는다면 workbook.getSheet("찾는 시트의 이름");
	            // 만약 모든 시트를 순회하고 싶으면
	            // for(Integer sheetNum : workbook.getNumberOfSheets()) {
	            //      XSSFSheet sheet = workbook.getSheetAt(i);
	            // }
	            // 아니면 Iterator<Sheet> s = workbook.iterator() 를 사용해서 조회.

	            // 모든 행(row)들을 조회한다.
	            Iterator<Row> rowIterator = sheet.iterator();
	            List<Cup> discardCupList = new ArrayList<Cup>();
	            Cup cup = null;
	            int rowIdx = 0;
	            while (rowIterator.hasNext()) {
	                Row row = rowIterator.next();
	                if(rowIdx != 0) {                	
	                	cup = new Cup();
	                	// 각각의 행에 존재하는 모든 열(cell)을 순회한다.
	                	Iterator<Cell> cellIterator = row.cellIterator();
	                	int cellIdx = 0;
	                	while (cellIterator.hasNext()) {
	                		Cell cell = cellIterator.next();
	                		String value = "";
	                		// cell의 타입을 하고, 값을 가져온다.
	                		switch (cell.getCellType()) {
		                		case NUMERIC:
		                			value = cell.getNumericCellValue() + "";
		                			break;
		                		case STRING:
		                			value = cell.getStringCellValue() + "";
		                			break;
			                	case FORMULA:
			                		value = cell.getCellFormula() + "";
			                		break;
			                	case BLANK:
			                		value = cell.getBooleanCellValue() + "";
			                		break;
			                	case ERROR:
			                		value = cell.getErrorCellValue() + "";
			                		break;
		                	}
	                		switch (cellIdx) {
							case 0: // 회원 코드
								cup.setPartnerInfo(value);
								break;
							case 1: // 컵 큐알 코드
								cup.setCupQR(value);
								break;
							}
	                		cellIdx += 1;
	                	}
	                	discardCupList.add(cup);
	                }
	                rowIdx += 1;
	            }
	            spreadsheetFile.close();
	            return discardCupList;
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
			return null;
		}
	}
