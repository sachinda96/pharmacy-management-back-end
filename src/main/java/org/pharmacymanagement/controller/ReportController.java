package org.pharmacymanagement.controller;

import org.pharmacymanagement.dto.OrderDate;
import org.pharmacymanagement.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@CrossOrigin
@RequestMapping("report")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping(value = "/item",produces = "application/pdf")
    public ResponseEntity<?> allItemReport()throws Exception{
        return reportService.allItemReport();
    }

    @GetMapping(value = "/customer",produces = "application/pdf")
    public ResponseEntity<?> allCustomerReport()throws Exception{
        return reportService.allCustomerReport();
    }

    @PostMapping(value = "/order",produces = "application/pdf")
    public ResponseEntity<?> allOrderReport(@RequestBody OrderDate orderDate)throws Exception{
        return reportService.allOrderReport(orderDate.getStartDate(), orderDate.getEndDate());
    }
}
