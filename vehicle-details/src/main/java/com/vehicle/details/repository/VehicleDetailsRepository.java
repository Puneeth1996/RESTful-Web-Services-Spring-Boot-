package com.vehicle.details.repository;

import com.vehicle.details.entity.VehicleDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleDetailsRepository extends JpaRepository<VehicleDetail,Integer> {
}
