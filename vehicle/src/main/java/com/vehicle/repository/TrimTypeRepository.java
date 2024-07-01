package com.vehicle.repository;

import com.vehicle.entity.TrimType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrimTypeRepository extends JpaRepository<TrimType, Integer> {
}
