package com.vehicle.service;

import com.vehicle.entity.Manufacturer;
import com.vehicle.exception.ManufacturerNotFoundException;

import java.util.List;

public interface ManufacturerService {

    Manufacturer saveManufacturer(Manufacturer manufacturer);
    List<Manufacturer> fetchAllManufacturers();
    Manufacturer getManufacturerForId(int id);
    Manufacturer updateManufacturer(int id, Manufacturer updatedManufacturer);
    void deleteManufacturerById(int id) throws ManufacturerNotFoundException;

}
