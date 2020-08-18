package org.pharmacymanagement.service.impl;

import org.pharmacymanagement.dto.LoginDto;
import org.pharmacymanagement.dao.UserDao;
import org.pharmacymanagement.entity.UserEntity;
import org.pharmacymanagement.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserDao userDao;

    @Override
    public ResponseEntity<?> login(LoginDto loginDto) {

        try {

            Optional<UserEntity> userEntity = userDao.findOneByUserName(loginDto.getUserName());

            if(userEntity.isPresent()){

                if(userEntity.get().getPassword().equalsIgnoreCase(loginDto.getPassword())){
                    return new ResponseEntity<>(userEntity.get().getId(), HttpStatus.OK);
                }

            }

            throw new Exception("Invalid User Failed to Login");
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
