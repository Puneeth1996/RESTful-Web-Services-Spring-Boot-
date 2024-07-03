package com.vehicle.details.repository;

import com.vehicle.details.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  UserRepo extends JpaRepository<User, Integer> {
    public User findByUsername(String username);
}