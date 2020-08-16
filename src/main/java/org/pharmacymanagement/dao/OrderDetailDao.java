package org.pharmacymanagement.dao;

import org.pharmacymanagement.entity.OrderDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailDao extends JpaRepository<OrderDetailsEntity ,String> {
}
