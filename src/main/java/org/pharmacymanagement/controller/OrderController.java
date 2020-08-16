package org.pharmacymanagement.controller;


import org.pharmacymanagement.dto.OrderDto;
import org.pharmacymanagement.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("order")
@CrossOrigin
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<?> placeOrder(@RequestBody List<OrderDto> orderDtoList){
        return orderService.placeOrder(orderDtoList);
    }
}
