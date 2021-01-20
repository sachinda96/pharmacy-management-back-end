package org.pharmacymanagement.service.impl;

import org.pharmacymanagement.dao.UserDao;
import org.pharmacymanagement.dto.UserDto;
import org.pharmacymanagement.entity.UserEntity;
import org.pharmacymanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public ResponseEntity<?> add(UserDto userDto) {

        try {

            Optional<UserEntity> userEntity = userDao.findOneByUserName(userDto.getUserName());

            if(userEntity.isPresent()){
                throw new Exception("Already taken this username");
            }
            userDao.save(setUserEntity(userDto));
            return new ResponseEntity<>("200", HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }

    @Override
    public ResponseEntity<?> getAll() {
        try {

            List<UserEntity> userEntities = userDao.findAll();

            List<UserDto> userDtoList = new ArrayList<>();

            if(userEntities != null){
                for (UserEntity userEntity : userEntities) {
                    userDtoList.add(setUserDto(userEntity));

                }
            }
            return new ResponseEntity<>(userDtoList,HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    public UserDto setUserDto(UserEntity userEntity) {
        UserDto userDto = new UserDto();
        userDto.setAddress(userEntity.getAddress());
        userDto.setId(userEntity.getId());
        userDto.setMobileNo(userEntity.getMobileNo());
        userDto.setRole(userEntity.getRole());
        userDto.setUserName(userEntity.getUserName());
        userDto.setNicNo(userEntity.getNicNo());
        userDto.setName(userEntity.getName());
        return userDto;
    }

    @Override
    public ResponseEntity<?> remove(String id) {

        try {

            userDao.deleteById(id);
            return new ResponseEntity<>("200",HttpStatus.OK);

        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @Override
    public ResponseEntity<?> firstUser() {

        try {

            String user ="admin";
            String password = "1234";
            Optional<UserEntity> userEntity = userDao.findOneByUserName(user);

            if(!userEntity.isPresent()){
                UserDto userDto = new UserDto();
                userDto.setUserName(user);
                userDto.setName(user);
                userDto.setPassword(password);
                userDao.save(setUserEntity(userDto));
                return new ResponseEntity<>("200",HttpStatus.OK);
            }
            return new ResponseEntity<>("204",HttpStatus.NO_CONTENT);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }

    @Override
    public ResponseEntity<?> getUserRole(String id) {

        try {

            UserEntity userEntity =userDao.findById(id).get();
            UserDto userDto = new UserDto();
            userDto.setRole(userEntity.getRole());

            return new ResponseEntity<>(userDto,HttpStatus.OK);

        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    public UserEntity setUserEntity(UserDto userDto) {
        UserEntity userEntity = new UserEntity();
        userEntity.setAddress(userDto.getAddress());
        userEntity.setId(UUID.randomUUID().toString());
        userEntity.setMobileNo(userDto.getMobileNo());
        userEntity.setName(userDto.getName());
        userEntity.setNicNo(userDto.getNicNo());
        userEntity.setPassword(userDto.getPassword());
        userEntity.setRole(userDto.getRole());
        userEntity.setUserName(userDto.getUserName());
        return userEntity;
    }
}
