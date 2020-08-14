package org.pharmacymanagement.dao;

import org.pharmacymanagement.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemDao extends JpaRepository<ItemEntity,String> {
    List<ItemEntity> findAllByStatus(String active);
}
