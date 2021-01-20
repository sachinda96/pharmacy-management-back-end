package org.pharmacymanagement.service;

import org.pharmacymanagement.dto.UserDto;
import org.springframework.http.ResponseEntity;

public interface UserService {

    public ResponseEntity<?> add(UserDto userDto);

    public ResponseEntity<?> getAll();

    public ResponseEntity<?> remove(String id);

    public ResponseEntity<?> firstUser();

    public ResponseEntity<?> getUserRole(String id);

}
