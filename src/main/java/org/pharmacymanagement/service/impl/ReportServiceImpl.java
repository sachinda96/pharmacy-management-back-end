package org.pharmacymanagement.service.impl;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import org.pharmacymanagement.dao.CustomerDao;
import org.pharmacymanagement.dao.ItemDao;
import org.pharmacymanagement.dao.OrderDao;
import org.pharmacymanagement.entity.CustomerEntity;
import org.pharmacymanagement.entity.ItemEntity;
import org.pharmacymanagement.entity.OrderDetailsEntity;
import org.pharmacymanagement.entity.OrderEntity;
import org.pharmacymanagement.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private ItemDao itemDao;

    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private OrderDao orderDao;

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

        try {

            List<OrderEntity> orderEntityList =orderDao.findAll();

            orderEntityList = orderEntityList.stream().filter(e-> e.getOrderDate().after(startDate) && e.getOrderDate().before(endDate)).collect(Collectors.toList());

            List<OrderDetailsEntity> orderDetailsEntities = new ArrayList<>();

            if(orderEntityList != null){

                for (OrderEntity orderEntity : orderEntityList) {
                    if(orderEntity.getOrderDetailsEntities()!= null){
                        for (OrderDetailsEntity orderDetailsEntity : orderEntity.getOrderDetailsEntities()) {
                            orderDetailsEntities.add(orderDetailsEntity);
                        }
                    }


                }
            }


            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

            PdfWriter writer = new PdfWriter(byteArrayOutputStream);

            PdfDocument pdf = new PdfDocument(writer);

            com.itextpdf.layout.Document doc = new Document(pdf);

            Paragraph paragraph1 = new Paragraph("All Customer Details").setFontSize(15).setBold();

            doc.add(paragraph1);

            doc.add(new Paragraph(""));
            doc.add(new Paragraph(""));

            float [] pointColumnWidths = {150F, 100F, 250F,100F};
            Table table = new Table(pointColumnWidths);


            table.addCell(new Cell().add("Customer Name").setBold().setFontSize(12));
            table.addCell(new Cell().add("Item Name").setBold().setFontSize(12));
            table.addCell(new Cell().add("Order Date").setBold().setFontSize(12));
            table.addCell(new Cell().add("Qty").setBold().setFontSize(12));


            if(orderDetailsEntities != null){
                for (OrderDetailsEntity orderDetailsEntity : orderDetailsEntities) {
                    table.addCell(new Cell().add(orderDetailsEntity.getCustomerName()).setFontSize(10));
                    table.addCell(new Cell().add(orderDetailsEntity.getItemEntity().getName()).setFontSize(10));
                    table.addCell(new Cell().add(dateToString(orderDetailsEntity.getOrderEntity().getOrderDate())).setFontSize(10));
                    table.addCell(new Cell().add(orderDetailsEntity.getQty().toString()).setFontSize(10));
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


    public String dateToString(Date date)throws Exception{
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        return dateFormat.format(date);
    }
}
