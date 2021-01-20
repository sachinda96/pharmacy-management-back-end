package org.pharmacymanagement.dao;

import org.pharmacymanagement.entity.SupplierEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SupplierDao extends JpaRepository<SupplierEntity,String> {
    List<SupplierEntity> findAllByStatus(String active);
}
