package org.pharmacymanagement.service;

import org.pharmacymanagement.dto.LoginDto;
import org.springframework.http.ResponseEntity;

public interface LoginService {

    public ResponseEntity<?> login(LoginDto loginDto);
}
