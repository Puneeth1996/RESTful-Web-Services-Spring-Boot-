package com.vehicle.repository;

import com.vehicle.entity.VehicleMarketPrice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleMarketPriceRepository extends JpaRepository<VehicleMarketPrice,Integer> {

    VehicleMarketPrice findByBrandNameAndModelName(String brandName, String modelName);
}