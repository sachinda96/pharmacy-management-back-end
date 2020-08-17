package org.pharmacymanagement;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;

import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class test {
    public static void main(String args[])throws Exception{

        String dest = "E:/addingTable.pdf";

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(byteArrayOutputStream);

        // Creating a PdfDocument object
        PdfDocument pdf = new PdfDocument(writer);

        // Creating a Document object
        com.itextpdf.layout.Document doc = new Document(pdf);

        Paragraph paragraph1 = new Paragraph("Stock Item Details").setFontSize(15).setBold();

        // Adding paragraphs to document
        doc.add(paragraph1);

        doc.add(new Paragraph(""));
        doc.add(new Paragraph(""));

        // Creating a table
        float [] pointColumnWidths = {150F, 250F, 100F,100F};
        Table table = new Table(pointColumnWidths);

        // Adding cells to the table
        table.addCell(new Cell().add("Item Name").setBold().setFontSize(12));
        table.addCell(new Cell().add("Item Description").setBold().setFontSize(12));
        table.addCell(new Cell().add("Item Price ").setBold().setFontSize(12));
        table.addCell(new Cell().add("Item Qty").setBold().setFontSize(12));


        for (int i =0;i<10;i++ ){
            table.addCell(new Cell().add("Item Name").setFontSize(10));
            table.addCell(new Cell().add("Item Description").setFontSize(10));
            table.addCell(new Cell().add("Item Price ").setFontSize(10));
            table.addCell(new Cell().add("Item Qty").setFontSize(10));
        }



        doc.add(table);

        // Closing the document
        doc.close();
        System.out.println("Table created successfully..");
    }
}
