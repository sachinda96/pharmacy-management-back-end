package org.pharmacymanagement.dao;

import org.pharmacymanagement.entity.SupplierOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierOrderDao extends JpaRepository<SupplierOrderEntity,String> {
}
