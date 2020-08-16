package org.pharmacymanagement.dao;

import org.pharmacymanagement.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDao extends JpaRepository<OrderEntity ,String> {
}
