package org.pharmacymanagement.service;

import org.springframework.http.ResponseEntity;

public interface DashboardService {

    public ResponseEntity<?> totalOrders();

    public ResponseEntity<?> totalItems();

    public ResponseEntity<?> totalCustomers();

    public ResponseEntity<?> totalUser();

    public ResponseEntity<?> totalRemovedItems();

    public ResponseEntity<?> totalRemovedCustomer();

    public ResponseEntity<?> totalItemNoValue();

    public ResponseEntity<?> topSevenOrders();
}
