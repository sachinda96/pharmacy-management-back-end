package org.pharmacymanagement.service;

import org.pharmacymanagement.dto.CustomerDto;
import org.pharmacymanagement.dto.ItemDto;
import org.springframework.http.ResponseEntity;

public interface ItemService {
    public ResponseEntity<?> save(ItemDto itemDto);

    public ResponseEntity<?> getAll();

    public ResponseEntity<?> delete(String id);

    public ResponseEntity<?> search(String id);
}
