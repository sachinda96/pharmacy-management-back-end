package org.pharmacymanagement.service.impl;

import org.pharmacymanagement.dao.ItemDao;
import org.pharmacymanagement.dao.SupplierDao;
import org.pharmacymanagement.dao.SupplierOrderDao;
import org.pharmacymanagement.dao.SupplierOrderDetailsDao;
import org.pharmacymanagement.dto.ItemDto;
import org.pharmacymanagement.dto.SupplierDto;
import org.pharmacymanagement.dto.SupplierOrderDto;
import org.pharmacymanagement.entity.ItemEntity;
import org.pharmacymanagement.entity.SupplierEntity;
import org.pharmacymanagement.entity.SupplierOrderDetailEntity;
import org.pharmacymanagement.entity.SupplierOrderEntity;
import org.pharmacymanagement.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class SupplierServiceImpl implements SupplierService {

    @Autowired
    private SupplierDao supplierDao;

    @Autowired
    private SupplierOrderDao supplierOrderDao;

    @Autowired
    private SupplierOrderDetailsDao supplierOrderDetailsDao;

    @Autowired
    private ItemDao itemDao;


    @Override
    public ResponseEntity<?> save(SupplierDto supplierDto) {

        try {

            SupplierEntity supplierEntity = null;
            if(supplierDto.getId() != null){
                supplierEntity = supplierDao.findById(supplierDto.getId()).get();
            }else{
                supplierEntity = new SupplierEntity();
                supplierEntity.setId(UUID.randomUUID().toString());
                supplierEntity.setCreateDate(new Date());
                supplierEntity.setStatus("ACTIVE");
            }

            supplierEntity.setName(supplierDto.getName());
            supplierEntity.setAddress(supplierDto.getAddress());
            supplierEntity.setEmail(supplierDto.getEmail());
            supplierEntity.setTelNo(supplierDto.getTelNo());
            supplierEntity.setCompany(supplierDto.getCompany());

            supplierDao.save(supplierEntity);

            return new ResponseEntity<>("200", HttpStatus.OK);

        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }

    @Override
    public ResponseEntity<?> getAll() {

        try {

            List<SupplierEntity> supplierEntityList = supplierDao.findAllByStatus("ACTIVE");

            List<SupplierDto> supplierDtos = new ArrayList<>();
            if(!supplierEntityList.isEmpty()){

                for (SupplierEntity supplierEntity : supplierEntityList) {
                    supplierDtos.add(setSupplierDto(supplierEntity));
                }

            }

            return new ResponseEntity<>(supplierDtos,HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }

    @Override
    public ResponseEntity<?> delete(String id) {

        try {

            SupplierEntity supplierEntity = supplierDao.findById(id).get();

            supplierEntity.setStatus("INACTIVE");

            supplierDao.save(supplierEntity);

            return new ResponseEntity<>("200",HttpStatus.OK);

        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(),HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<?> search(String id) {

        try {

            SupplierEntity supplierEntity = supplierDao.findById(id).get();

            return new ResponseEntity<>(setSupplierDto(supplierEntity),HttpStatus.OK);

        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(),HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<?> supplierOrder(SupplierOrderDto supplierOrderDto) {

        try {

            Optional<SupplierEntity> supplierEntity = supplierDao.findById(supplierOrderDto.getSupplier().getId());

            if(supplierEntity.isPresent()){
                supplierEntity.orElseThrow(()->new Exception("Invalid Supplier Details"));
            }

            if(!supplierOrderDto.getItemList().isEmpty()){

                SupplierOrderEntity supplierOrderEntity = new SupplierOrderEntity();
                supplierOrderEntity.setId(UUID.randomUUID().toString());
                supplierOrderEntity.setOrderDate(new Date());
                supplierOrderEntity.setSupplierEntity(supplierEntity.get());
                supplierOrderEntity = supplierOrderDao.save(supplierOrderEntity);

                double total = 0.00;

                for (ItemDto itemDto : supplierOrderDto.getItemList()) {
                    ItemEntity itemEntity = setItemEntity(itemDto);
                    total = new BigDecimal(total).add(new BigDecimal(itemEntity.getPrice()).multiply(new BigDecimal(itemEntity.getQty()))).setScale(2).doubleValue();
                    itemDao.save(itemEntity);

                    SupplierOrderDetailEntity supplierOrderDetailEntity = new SupplierOrderDetailEntity();
                    supplierOrderDetailEntity.setSupplierOrderEntity(supplierOrderEntity);
                    supplierOrderDetailEntity.setItemEntity(itemEntity);
                    supplierOrderDetailEntity.setId(UUID.randomUUID().toString());
                    supplierOrderDetailsDao.save(supplierOrderDetailEntity);
                }

                supplierOrderEntity.setTotal(total);
                supplierOrderDao.save(supplierOrderEntity);

            }

            return new ResponseEntity<>("200",HttpStatus.OK);

        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }

    private SupplierDto setSupplierDto(SupplierEntity supplierEntity){
        SupplierDto supplierDto = new SupplierDto();
        supplierDto.setId(supplierEntity.getId());
        supplierDto.setCompany(supplierEntity.getCompany());
        supplierDto.setEmail(supplierEntity.getEmail());
        supplierDto.setAddress(supplierEntity.getAddress());
        supplierDto.setName(supplierEntity.getName());
        supplierDto.setTelNo(supplierEntity.getTelNo());
        return  supplierDto;
    }

    private ItemEntity setItemEntity(ItemDto itemDto){

        ItemEntity itemEntity = new ItemEntity();
        itemEntity.setLocation("HO");
        itemEntity.setCreateDate(new Date());
        itemEntity.setExpireDate(itemDto.getExpDate());
        itemEntity.setName(itemDto.getName());
        itemEntity.setId(UUID.randomUUID().toString());
        itemEntity.setPrice(itemDto.getPrice());
        itemEntity.setStatus("ACTIVE");
        itemEntity.setQty(itemDto.getQty());
        itemEntity.setDescription(itemDto.getDescription());
        return itemEntity;

    }
}
