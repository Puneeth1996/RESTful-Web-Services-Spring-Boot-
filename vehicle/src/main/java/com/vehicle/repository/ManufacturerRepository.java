package com.vehicle.repository;

import com.vehicle.entity.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManufacturerRepository extends JpaRepository<Manufacturer, Integer> {
    Manufacturer findByManufacturerName(String manufacturerName);
}
