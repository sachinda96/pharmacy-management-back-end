package org.pharmacymanagement.dao;

import org.pharmacymanagement.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerDao extends JpaRepository<CustomerEntity ,String> {
    List<CustomerEntity> findAllByStatus(String active);

    long countAllByStatus(String acTiVE);
}
