package com.gs.pdf;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

public class CreatePDF {
	
	public boolean convertTextToPDF(String filePath) {

		try {
			File file = new File(filePath);
		    if (file.exists()) {
		        Document pdfDoc = new Document(PageSize.A4);
		        String output_file = file.getAbsolutePath().replace(".txt", ".pdf");
		        PdfWriter writer = PdfWriter.getInstance(pdfDoc, new FileOutputStream(output_file));	
		        writer.setPdfVersion(PdfWriter.VERSION_1_7);
		        HeaderAndFooter footer = new HeaderAndFooter();
		        writer.setPageEvent(footer);
		        pdfDoc.open();
	       
		        BaseFont bf = BaseFont.createFont(BaseFont.COURIER, BaseFont.CP1250, BaseFont.NOT_EMBEDDED);
		        Font myfont = new Font(bf);
		        myfont.setStyle(Font.NORMAL);
		        myfont.setSize(6);
	
		        BufferedReader br = new BufferedReader(new FileReader(file));
		        String strLine;
	
		        while ((strLine = br.readLine()) != null) {
		        	Paragraph para = new Paragraph(strLine + "\n", myfont);
		        	para.setAlignment(Element.ALIGN_JUSTIFIED);
		        	pdfDoc.add(para);
		        }
               
		        pdfDoc.close();		        
		        br.close();
		        file.delete();
		    } else {
		    	System.out.println("no such file exists!");
		    	return false;
		    }
	    } catch (IOException | DocumentException e) {
			e.printStackTrace();
			return false;
		}

	    return true;
	}
	
	public static void main(String[] args) {
		CreatePDF createPDF = new CreatePDF();
		try {
			createPDF.convertTextToPDF("C:/Users/GIHAN/Downloads/AOWF_Audit_Log_(2021-11-25).txt");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
