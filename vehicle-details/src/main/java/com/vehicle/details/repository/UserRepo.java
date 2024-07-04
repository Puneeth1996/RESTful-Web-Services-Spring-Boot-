package com.vehicle.details.repository;

import com.vehicle.details.entity.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  UserRepo extends JpaRepository<UserDetail, Integer> {
    public UserDetail findByUsername(String username);
}