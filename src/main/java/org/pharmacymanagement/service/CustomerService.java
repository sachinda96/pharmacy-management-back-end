package org.pharmacymanagement.service;

import org.pharmacymanagement.dto.CustomerDto;
import org.springframework.http.ResponseEntity;

public interface CustomerService {

    public ResponseEntity<?> save(CustomerDto customerDto);

    public ResponseEntity<?> getAll();

    public ResponseEntity<?> delete(String id);

    public ResponseEntity<?> search(String id);
}
