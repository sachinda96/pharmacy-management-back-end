package org.pharmacymanagement.service.impl;

import org.pharmacymanagement.dao.ItemDao;
import org.pharmacymanagement.dto.CustomerDto;
import org.pharmacymanagement.dto.ItemDto;
import org.pharmacymanagement.entity.CustomerEntity;
import org.pharmacymanagement.entity.ItemEntity;
import org.pharmacymanagement.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.*;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemDao itemDao;

    @Override
    public ResponseEntity<?> save(ItemDto itemDto) {
        try {

            ItemEntity itemEntity =null;
            if(itemDto.getId()!= null){
                itemEntity = itemDao.findById(itemDto.getId()).get();
            }else{
                itemEntity = new ItemEntity();
                itemEntity.setId(UUID.randomUUID().toString());
                itemEntity.setCreateBy(itemDto.getUser());
                itemEntity.setCreateDate(new Date());
            }

            itemEntity.setDescription(itemDto.getDescription());
            itemEntity.setExpireDate(itemDto.getExpDate());
            itemEntity.setName(itemDto.getName());
            itemEntity.setPrice(itemDto.getPrice());
            itemEntity.setQty(itemDto.getQty());
            itemEntity.setType(itemDto.getType());
            itemEntity.setStatus("ACTIVE");
            itemDao.save(itemEntity);

            return new ResponseEntity<>("200", HttpStatus.OK);

        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> getAll() {
        try {

            List<ItemDto> itemDtoList = new ArrayList<>();
            List<ItemEntity> itemEntityList = itemDao.findAllByStatus("ACTIVE");

            if(itemEntityList!=null){

                for (ItemEntity itemEntity:
                        itemEntityList) {

                    itemDtoList.add(setItemDto(itemEntity));


                }
            }

            return new ResponseEntity<>(itemDtoList,HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private ItemDto setItemDto(ItemEntity itemEntity) {
        ItemDto itemDto = new ItemDto();
        itemDto.setDescription(itemEntity.getDescription());
        itemDto.setExpDate(itemEntity.getExpireDate());
        itemDto.setId(itemEntity.getId());
        itemDto.setName(itemEntity.getName());
        itemDto.setPrice(itemEntity.getPrice());
        itemDto.setQty(itemEntity.getQty());
        itemDto.setType(itemEntity.getType());
        return itemDto;
    }

    @Override
    public ResponseEntity<?> delete(String id) {
        try {

            Optional<ItemEntity> itemEntity = itemDao.findById(id);

            if(itemEntity.isPresent()){
                itemEntity.get().setStatus("INACTIVE");
                itemDao.save(itemEntity.get());
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

            Optional<ItemEntity> itemEntity = itemDao.findById(id);

            if(itemEntity.isPresent()){
                return new ResponseEntity<>(setItemDto(itemEntity.get()),HttpStatus.OK);
            }
            throw new Exception("Invalid Customer Details");
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
