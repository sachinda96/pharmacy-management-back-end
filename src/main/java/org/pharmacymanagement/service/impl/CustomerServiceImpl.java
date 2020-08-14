package org.pharmacymanagement.service.impl;

import org.pharmacymanagement.dao.CustomerDao;
import org.pharmacymanagement.dto.CustomerDto;
import org.pharmacymanagement.entity.CustomerEntity;
import org.pharmacymanagement.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDao customerDao;

    @Override
    public ResponseEntity<?> save(CustomerDto customerDto) {
            try {

                CustomerEntity customerEntity =null;
                if(customerDto.getId()!= null){
                    customerEntity = customerDao.findById(customerDto.getId()).get();
                }else{
                    customerEntity = new CustomerEntity();
                    customerEntity.setId(UUID.randomUUID().toString());
                    customerEntity.setCreateBy(customerDto.getUser());
                    customerEntity.setCreateDate(new Date());
                }

                customerEntity.setAddress(customerDto.getAddress());
                customerEntity.setBirthDay(customerDto.getBirthDay());
                customerEntity.setCity(customerDto.getCity());
                customerEntity.setEmail(customerDto.getEmail());
                customerEntity.setFullName(customerDto.getFullName());
                customerEntity.setMobileNo(customerDto.getMobileNo());
                customerEntity.setNicNo(customerDto.getNicNo());
                customerEntity.setOccupation(customerDto.getOccupation());
                customerEntity.setPostalCode(customerDto.getPostalCode());
                customerEntity.setStatus("ACTIVE");
                customerDao.save(customerEntity);

                return new ResponseEntity<>("200", HttpStatus.OK);

            }catch (Exception e){
                e.printStackTrace();
                return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
            }
    }

    @Override
    public ResponseEntity<?> getAll() {

        try {

            List<CustomerDto> customerDtoList = new ArrayList<>();
            List<CustomerEntity> customerEntityList = customerDao.findAllByStatus("ACTIVE");

            if(customerEntityList!=null){

                for (CustomerEntity customerEntity:
                     customerEntityList) {

                    customerDtoList.add(setCustomerDto(customerEntity));


                }
            }

            return new ResponseEntity<>(customerDtoList,HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }

    public CustomerDto setCustomerDto(CustomerEntity customerEntity) {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setAddress(customerEntity.getAddress());
        customerDto.setBirthDay(customerEntity.getBirthDay());
        customerDto.setCity(customerEntity.getCity());
        customerDto.setEmail(customerEntity.getEmail());
        customerDto.setFullName(customerEntity.getFullName());
        customerDto.setId(customerEntity.getId());
        customerDto.setMobileNo(customerEntity.getMobileNo());
        customerDto.setNicNo(customerEntity.getNicNo());
        customerDto.setOccupation(customerEntity.getOccupation());
        customerDto.setPostalCode(customerEntity.getPostalCode());
        return customerDto;
    }

    @Override
    public ResponseEntity<?> delete(String id) {

        try {

            Optional<CustomerEntity> customerEntity = customerDao.findById(id);

            if(customerEntity.isPresent()){
                customerEntity.get().setStatus("INACTIVE");
                customerDao.save(customerEntity.get());
                return new ResponseEntity<>("200",HttpStatus.OK);
            }

            return new ResponseEntity<>("204",HttpStatus.NO_CONTENT);

        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> search(String id) {

        try {

            Optional<CustomerEntity> customerEntity = customerDao.findById(id);

            if(customerEntity.isPresent()){
                return new ResponseEntity<>(setCustomerDto(customerEntity.get()),HttpStatus.OK);
            }
            throw new Exception("Invalid Customer Details");
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }
}
