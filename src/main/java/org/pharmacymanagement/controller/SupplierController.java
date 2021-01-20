package org.pharmacymanagement.controller;

import org.pharmacymanagement.dao.SupplierOrderDao;
import org.pharmacymanagement.dto.ItemDto;
import org.pharmacymanagement.dto.SupplierDto;
import org.pharmacymanagement.dto.SupplierOrderDto;
import org.pharmacymanagement.service.ItemService;
import org.pharmacymanagement.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("supplier")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @PostMapping
    public ResponseEntity<?> save(@RequestBody SupplierDto supplierDto) {
        return supplierService.save(supplierDto);
    }

    @GetMapping
    public ResponseEntity<?> getAll(){
        return supplierService.getAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id){
        return supplierService.delete(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> search(@PathVariable String id){
        return supplierService.search(id);
    }

    @PostMapping("/order")
    public ResponseEntity<?> supplierOrder(@RequestBody SupplierOrderDto supplierOrderDto){
        return supplierService.supplierOrder(supplierOrderDto);
    }
}
