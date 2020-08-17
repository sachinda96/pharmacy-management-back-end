package org.pharmacymanagement.service.impl;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import org.pharmacymanagement.dao.CustomerDao;
import org.pharmacymanagement.dao.ItemDao;
import org.pharmacymanagement.entity.CustomerEntity;
import org.pharmacymanagement.entity.ItemEntity;
import org.pharmacymanagement.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.Date;
import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private ItemDao itemDao;

    @Autowired
    private CustomerDao customerDao;

    @Override
    public ResponseEntity<?> allItemReport()throws Exception {

        try {


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

            List<ItemEntity> itemEntityList = itemDao.findAllByStatus("ACTIVE");

            if(itemEntityList != null){
                for (ItemEntity itemEntity : itemEntityList) {
                    table.addCell(new Cell().add(itemEntity.getName()).setFontSize(10));
                    table.addCell(new Cell().add(itemEntity.getDescription()).setFontSize(10));
                    table.addCell(new Cell().add(itemEntity.getPrice().toString()).setFontSize(10));
                    table.addCell(new Cell().add(itemEntity.getQty().toString()).setFontSize(10));
                }
            }

            doc.add(table);
            doc.close();

            return new ResponseEntity<>(byteArrayOutputStream.toByteArray(),HttpStatus.OK);

        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }



    }

    @Override
    public ResponseEntity<?> allCustomerReport() {

        try {

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            PdfWriter writer = new PdfWriter(byteArrayOutputStream);

            // Creating a PdfDocument object
            PdfDocument pdf = new PdfDocument(writer);

            // Creating a Document object
            com.itextpdf.layout.Document doc = new Document(pdf);

            Paragraph paragraph1 = new Paragraph("All Customer Details").setFontSize(15).setBold();

            // Adding paragraphs to document
            doc.add(paragraph1);

            doc.add(new Paragraph(""));
            doc.add(new Paragraph(""));

            // Creating a table
            float [] pointColumnWidths = {150F, 100F, 250F,100F};
            Table table = new Table(pointColumnWidths);

            // Adding cells to the table
            table.addCell(new Cell().add("Full Name").setBold().setFontSize(12));
            table.addCell(new Cell().add("Nic No").setBold().setFontSize(12));
            table.addCell(new Cell().add("Address").setBold().setFontSize(12));
            table.addCell(new Cell().add("Mobile No").setBold().setFontSize(12));

            List<CustomerEntity> customerEntities = customerDao.findAllByStatus("ACTIVE");

            if(customerEntities != null){
                for (CustomerEntity customerEntity : customerEntities) {
                    table.addCell(new Cell().add(customerEntity.getFullName()).setFontSize(10));
                    table.addCell(new Cell().add(customerEntity.getNicNo()).setFontSize(10));
                    table.addCell(new Cell().add(customerEntity.getAddress()).setFontSize(10));
                    table.addCell(new Cell().add(customerEntity.getMobileNo()).setFontSize(10));
                }
            }

            doc.add(table);
            doc.close();

            return new ResponseEntity<>(byteArrayOutputStream.toByteArray(),HttpStatus.OK);


        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }

    @Override
    public ResponseEntity<?> allOrderReport(Date startDate, Date endDate) {
        return null;
    }
}
