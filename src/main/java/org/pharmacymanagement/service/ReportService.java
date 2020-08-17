package org.pharmacymanagement.service;

import org.springframework.http.ResponseEntity;

import java.util.Date;

public interface ReportService {


    public ResponseEntity<?> allItemReport()throws Exception;

    public ResponseEntity<?> allCustomerReport();

    public ResponseEntity<?> allOrderReport(Date startDate ,Date endDate);
}
