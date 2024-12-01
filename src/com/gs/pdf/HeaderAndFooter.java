package com.gs.pdf;

import java.io.IOException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

public class HeaderAndFooter extends PdfPageEventHelper {

	public void onEndPage (PdfWriter writer, Document document) {
		
		try {
			BaseFont bf = BaseFont.createFont(BaseFont.COURIER, BaseFont.CP1250, BaseFont.NOT_EMBEDDED);
			Font myfont = new Font(bf);
	        myfont.setStyle(Font.NORMAL);
	        myfont.setSize(6);
	        
	        ColumnText.showTextAligned(writer.getDirectContent(),
	                Element.ALIGN_RIGHT, new Phrase(String.format("| Page %d", writer.getPageNumber()), myfont),
	                document.right(), document.bottom()-10, 0);
	        
		} catch (DocumentException | IOException e) {
			e.printStackTrace();
		}        
	}   
}    