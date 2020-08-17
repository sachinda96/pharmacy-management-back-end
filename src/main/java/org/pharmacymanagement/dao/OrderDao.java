package org.pharmacymanagement.dao;

import org.pharmacymanagement.entity.OrderDetailsEntity;
import org.pharmacymanagement.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDao extends JpaRepository<OrderEntity ,String> {

}
