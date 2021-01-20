package org.pharmacymanagement.dao;

import org.pharmacymanagement.entity.SupplierOrderDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierOrderDetailsDao extends JpaRepository<SupplierOrderDetailEntity,String> {
}
