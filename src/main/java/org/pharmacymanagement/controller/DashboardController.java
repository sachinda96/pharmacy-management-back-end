package org.pharmacymanagement.controller;

import org.pharmacymanagement.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("dashboard")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @GetMapping("/totalOrders")
    public ResponseEntity<?> totalOrders(){
        return dashboardService.totalOrders();
    }

    @GetMapping("/totalItems")
    public ResponseEntity<?> totalItems(){
        return dashboardService.totalItems();
    }

    @GetMapping("/totalCustomers")
    public ResponseEntity<?> totalCustomers(){
        return dashboardService.totalCustomers();
    }

    @GetMapping("/totalUser")
    public ResponseEntity<?> totalUser(){
        return dashboardService.totalUser();
    }

    @GetMapping("/totalRemovedItems")
    public ResponseEntity<?> totalRemovedItems(){
        return dashboardService.totalRemovedItems();
    }

    @GetMapping("/totalRemovedCustomer")
    public ResponseEntity<?> totalRemovedCustomer(){
        return dashboardService.totalRemovedCustomer();
    }

    @GetMapping("/totalItemNoValue")
    public ResponseEntity<?> totalItemNoValue(){
        return dashboardService.totalItemNoValue();
    }

    @GetMapping("/topSevenOrders")
    public ResponseEntity<?> topSevenOrders(){
        return dashboardService.topSevenOrders();
    }

}
