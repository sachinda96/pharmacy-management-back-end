package org.pharmacymanagement.controller;

import org.pharmacymanagement.dto.CustomerDto;
import org.pharmacymanagement.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("customer")
@CrossOrigin
@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping
    public ResponseEntity<?> save(@RequestBody CustomerDto customerDto){
        return customerService.save(customerDto);
    }

    @GetMapping
    public ResponseEntity<?> getAll(){
        return customerService.getAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id){
        return customerService.delete(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> search(@PathVariable String id){
        return customerService.search(id);
    }
}
