package com.hms.service;

import com.hms.entity.Property;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;

@Service
public class PDFService {
    // Implement the functionality to generate PDFs here

    public void generateBookingPDF(String path, Property property){

       try {
           Document document = new Document();
           PdfWriter.getInstance(document, new FileOutputStream(path));

           document.open();
           PdfPTable table = new PdfPTable(1);
           table.addCell("Hotel name : "+property.getName());

           document.add(table);
           document.close();
       }catch (Exception e){
           e.printStackTrace();
       }

    }
}
