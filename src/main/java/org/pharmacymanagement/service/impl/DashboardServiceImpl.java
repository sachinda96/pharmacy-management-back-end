package org.pharmacymanagement.service.impl;

import org.pharmacymanagement.dao.CustomerDao;
import org.pharmacymanagement.dao.ItemDao;
import org.pharmacymanagement.dao.OrderDao;
import org.pharmacymanagement.dao.OrderDetailDao;
import org.pharmacymanagement.dto.OrderDetailsDto;
import org.pharmacymanagement.entity.OrderDetailsEntity;
import org.pharmacymanagement.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DashboardServiceImpl implements DashboardService {

    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private ItemDao itemDao;

    @Autowired
    private OrderDetailDao orderDetailDao;

    @Autowired
    private OrderDao orderDao;

    @Override
    public ResponseEntity<?> totalOrders() {

        try {

            return new ResponseEntity<>(orderDao.count(),HttpStatus.OK);

        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }

    @Override
    public ResponseEntity<?> totalItems() {
        try {

            return new ResponseEntity<>(itemDao.countAllByStatus("ACTiVE"),HttpStatus.OK);

        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @Override
    public ResponseEntity<?> totalCustomers() {
        try {

            return new ResponseEntity<>(customerDao.countAllByStatus("ACTiVE"),HttpStatus.OK);

        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> totalUser() {
        return null;
    }

    @Override
    public ResponseEntity<?> totalRemovedItems() {

        try {
            return new ResponseEntity<>(itemDao.countAllByStatus("INACTiVE"),HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> totalRemovedCustomer() {
        try {
            return new ResponseEntity<>(customerDao.countAllByStatus("INACTiVE"),HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> totalItemNoValue() {
        try {
            return new ResponseEntity<>(itemDao.countAllByStatusAndQty("ACTiVE",0),HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> topSevenOrders() {
        try {

            List<OrderDetailsEntity> orderDetailsEntityList = orderDetailDao.findAll();

            List<OrderDetailsDto> orderDetailsDtoList = new ArrayList<>();

            Integer seq = 0;

            if(orderDetailsEntityList != null){

                for (OrderDetailsEntity orderDetailsEntity : orderDetailsEntityList) {
                    if(seq == 7){
                        break;
                    }else {
                        seq =seq+1;

                        OrderDetailsDto orderDetailsDto = new OrderDetailsDto();
                        orderDetailsDto.setCustomerName(orderDetailsEntity.getCustomerName());
                        orderDetailsDto.setDate(orderDetailsEntity.getOrderEntity().getOrderDate());
                        orderDetailsDto.setItemName(orderDetailsEntity.getItemEntity().getName());
                        orderDetailsDto.setQty(orderDetailsEntity.getQty().toString());
                        orderDetailsDtoList.add(orderDetailsDto);

                    }

                }
            }


            return new ResponseEntity<>(orderDetailsDtoList,HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
