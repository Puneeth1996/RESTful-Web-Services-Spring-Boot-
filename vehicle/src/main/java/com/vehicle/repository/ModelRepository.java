package com.vehicle.repository;

import com.vehicle.entity.Manufacturer;
import com.vehicle.entity.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ModelRepository extends JpaRepository<Model, Integer> {

    List<Model> findByManufacturer(Manufacturer manufacturer);
    Model findByModelName(String name);

    @Query(value = "SELECT * FROM models WHERE manufacturer_id = ?1",nativeQuery = true)
    List<Model> fetchModelsBasedManufacturerId(int manufacturerId);
}
