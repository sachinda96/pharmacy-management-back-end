package org.pharmacymanagement.service.impl;

import org.pharmacymanagement.dao.ItemDao;
import org.pharmacymanagement.dao.OrderDao;
import org.pharmacymanagement.dao.OrderDetailDao;
import org.pharmacymanagement.dto.OrderDto;
import org.pharmacymanagement.entity.ItemEntity;
import org.pharmacymanagement.entity.OrderDetailsEntity;
import org.pharmacymanagement.entity.OrderEntity;
import org.pharmacymanagement.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class OrderServiceImpl implements OrderService {


    @Autowired
    private OrderDao orderDao;

    @Autowired
    private ItemDao itemDao;

    @Autowired
    private OrderDetailDao orderDetailDao;

    @Override
    public ResponseEntity<?> placeOrder(List<OrderDto> orderDtoList) {

        try {

            if(orderDtoList!= null){
                Double total = 0.0;
                String user = "";
                List<OrderDetailsEntity> orderDetailsEntities = new ArrayList<>();

                for (OrderDto orderDto : orderDtoList) {
                    total = new BigDecimal(total).add(new BigDecimal(orderDto.getPrice()).multiply(new BigDecimal(orderDto.getQty()))).setScale(2).doubleValue();

                    if(user.equalsIgnoreCase("")){
                        user = orderDto.getUser();
                    }
                    orderDetailsEntities.add(orderDetailsEntity(orderDto));
                }


                OrderEntity orderEntity=orderDao.save(setOrder(total,user));

                for (OrderDetailsEntity orderDetailsEntity : orderDetailsEntities) {
                    orderDetailsEntity.setOrderEntity(orderEntity);
                }

                orderDetailDao.saveAll(orderDetailsEntities);

                return new ResponseEntity<>("200",HttpStatus.OK);

            }


            return new ResponseEntity<>("204",HttpStatus.NO_CONTENT);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
        }


    }

    public OrderEntity setOrder(Double total,String user) {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setCreateBy(user);
        orderEntity.setCreateDate(new Date());
        orderEntity.setId(UUID.randomUUID().toString());
        orderEntity.setOrderDate(new Date());
        orderEntity.setTotal(total);
        return orderEntity;

    }

    public OrderDetailsEntity orderDetailsEntity(OrderDto orderDto)throws Exception{


        Optional<ItemEntity> itemEntity = itemDao.findById(orderDto.getItemId());

        itemEntity.orElseThrow(()-> new Exception("Invalid Item"));

        Integer validQty = new BigDecimal(itemEntity.get().getQty()).subtract(new BigDecimal(orderDto.getQty())).intValue();

        itemEntity.get().setQty(validQty);
        itemDao.save(itemEntity.get());

        OrderDetailsEntity orderDetailsEntity = new OrderDetailsEntity();
        orderDetailsEntity.setCreateBy(orderDto.getUser());
        orderDetailsEntity.setCreateDate(new Date());
        orderDetailsEntity.setCustomerName(orderDto.getCustomerName());
        orderDetailsEntity.setId(UUID.randomUUID().toString());
        orderDetailsEntity.setItemEntity(itemEntity.get());
        orderDetailsEntity.setPrice(orderDto.getPrice());
        orderDetailsEntity.setQty(orderDto.getQty());
        return orderDetailsEntity;

    }
}
