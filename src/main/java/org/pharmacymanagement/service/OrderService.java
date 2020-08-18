package org.pharmacymanagement.service;

import org.pharmacymanagement.dto.OrderDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface OrderService {

    public ResponseEntity<?> placeOrder(List<OrderDto> orderDtoList);

    public ResponseEntity<?> getAllOrders();
}
