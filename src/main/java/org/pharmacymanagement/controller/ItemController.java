package org.pharmacymanagement.controller;

import org.pharmacymanagement.dto.CustomerDto;
import org.pharmacymanagement.dto.ItemDto;
import org.pharmacymanagement.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("item")
@CrossOrigin
@RestController
public class ItemController {

    @Autowired
    private ItemService itemService;

    @PostMapping
    public ResponseEntity<?> save(@RequestBody ItemDto itemDto)
    {

        return itemService.save(itemDto);
    }

    @GetMapping
    public ResponseEntity<?> getAll(){
        return itemService.getAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id){
        return itemService.delete(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> search(@PathVariable String id){
        return itemService.search(id);
    }
}
