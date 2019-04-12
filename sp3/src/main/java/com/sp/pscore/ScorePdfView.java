package com.sp.pscore;

import java.awt.Color;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfWriter;

public class ScorePdfView extends AbstractPdfView {

	@SuppressWarnings("unchecked")
	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String fileName = (String)model.get("fileName");
		
		List<String> columnLabels = (List<String>)model.get("columnLabels");
		List<String[]> columnValues = (List<String[]>)model.get("columnValues");
		
		response.setContentType("application/pdf");
		response.setHeader("Content-disposition", "attachment; filename=" + fileName);
		
		BaseFont font = BaseFont.createFont("c:\\windows\\fonts\\gulim.ttc,0", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
		
		Font f = new Font(font);
		f.setSize(10);
		
		Table table = new Table(columnLabels.size(), columnValues.size() + 1);
		table.setPadding(5);
		
		Cell cell;
		
		for(int idx = 0; idx < columnLabels.size(); idx++) {
			cell = new Cell(new Paragraph(columnLabels.get(idx), f));
			cell.setBackgroundColor(new Color(255, 255, 0));
			cell.setHorizontalAlignment(Cell.ALIGN_CENTER);
			
			if(idx == 0) {
				cell.setHeader(true);
			}
			
			table.addCell(cell);
			
			if(idx == columnLabels.size() - 1) {
				table.endHeaders();
			}
		}
		
		for(int idx = 0; idx < columnValues.size(); idx++) {
			String[] values = columnValues.get(idx);
			
			for(int col = 0; col < values.length; col++) {
				cell = new Cell(new Paragraph(values[col], f));
				cell.setHorizontalAlignment(Cell.ALIGN_CENTER);
				table.addCell(cell);
			}
		}
		
		document.add(table);
	}

}
