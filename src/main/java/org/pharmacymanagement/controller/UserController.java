package org.pharmacymanagement.controller;

import org.pharmacymanagement.dto.UserDto;
import org.pharmacymanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<?> add(@RequestBody  UserDto userDto){
        return userService.add(userDto);
    }

    @GetMapping
    public ResponseEntity<?> getAll(){
        return userService.getAll();
    }

    @DeleteMapping
    public ResponseEntity<?> remove(@PathVariable String id){
        return  userService.remove(id);
    }

    @PutMapping
    public ResponseEntity<?> firstUser(){
        return userService.firstUser();
    }

}
