package org.pharmacymanagement.controller;

import org.pharmacymanagement.dto.LoginDto;
import org.pharmacymanagement.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto){
        return loginService.login(loginDto);
    }

}
