package org.pharmacymanagement.dao;

import org.pharmacymanagement.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDao extends JpaRepository<UserEntity,String> {
    Optional<UserEntity> findOneByUserName(String userName);
}
