package org.pharmacymanagement.service;

import org.pharmacymanagement.dto.SupplierDto;
import org.pharmacymanagement.dto.SupplierOrderDto;
import org.springframework.http.ResponseEntity;

public interface SupplierService {

    public ResponseEntity<?> save(SupplierDto supplierDto);

    public ResponseEntity<?> getAll();

    public ResponseEntity<?> delete(String id);

    public ResponseEntity<?> search(String id);

    public ResponseEntity<?> supplierOrder(SupplierOrderDto supplierOrderDto);

}
