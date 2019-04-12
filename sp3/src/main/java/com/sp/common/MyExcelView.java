package com.sp.common;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.document.AbstractXlsView;

// AbstractExcelView : spring 버전 4.2부터는 제거되었으며 AbstractXlsView로 처리
@Service("common.myExcelView")
public class MyExcelView extends AbstractXlsView {

	@SuppressWarnings("unchecked")
	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		String fileName = (String)model.get("fileName");
		String sheetName = (String)model.get("sheetName");
		List<String> columnLabels = (List<String>)model.get("columnLabels");
		List<Object[]> columnValues = (List<Object[]>)model.get("columnValues");
		
		response.setContentType("application/msexcel");
		response.setHeader("Content-disposition", "attachment; filename=" + fileName);
		
		Sheet sheet = createSheet(workbook, 0, sheetName);
		createColumnLabel(sheet, columnLabels);
		createColumnValue(sheet, columnValues);
	}
	
	// sheet 생성
	/**
	 * @param book (sheet를 작성할 WorkBook)
	 * @param sheetIdx (sheet 인덱스 0부터 시작)
	 * @param sheetName (sheet 이름)
	 * @return
	 */
	private Sheet createSheet(Workbook book, int sheetIdx, String sheetName) {
		Sheet sheet = book.createSheet();
		book.setSheetName(sheetIdx, sheetName);
		
		return sheet;
	}
	
	// 제목 (첫 번째 row에 입력)
	private void createColumnLabel(Sheet sheet, List<String> columnLabels) {
		Row row = sheet.createRow(0);
		Cell cell;
		
		for(int idx = 0; idx < columnLabels.size(); idx++) {
			sheet.setColumnWidth(idx, 256*15);
			cell = row.createCell(idx);
			cell.setCellValue(columnLabels.get(idx));
		}
	}
	
	// 내용 (두 번째 row에 입력)
	private void createColumnValue(Sheet sheet, List<Object[]> columnValues) {
		Row row;
		Cell cell;
		
		for(int idx = 0; idx < columnValues.size(); idx++) {
			row = sheet.createRow(idx + 1);
			
			Object[] values = columnValues.get(idx);
			
			for(int col = 0; col < values.length; col++) {
				cell = row.createCell(col);
				
				if(values[col] instanceof Short) {
					cell.setCellValue((Short)values[col]);
				} else if(values[col] instanceof Integer) {
					cell.setCellValue((Integer)values[col]);
				} else if(values[col] instanceof Long) {
					cell.setCellValue((Long)values[col]);
				} else if(values[col] instanceof Float) {
					cell.setCellValue((Float)values[col]);
				} else if(values[col] instanceof Double) {
					cell.setCellValue((Double)values[col]);
				} else if(values[col] instanceof Character) {
					cell.setCellValue((Character)values[col]);
				} else if(values[col] instanceof Boolean) {
					cell.setCellValue((Boolean)values[col]);
				} else if(values[col] instanceof String) {
					cell.setCellValue((String)values[col]);
				} else {
					cell.setCellValue(values[col].toString());
				}
			}
		}
	}
}
