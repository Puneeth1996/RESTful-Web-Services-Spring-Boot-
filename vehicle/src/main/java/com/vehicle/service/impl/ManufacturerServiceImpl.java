package com.vehicle.service.impl;

import com.vehicle.entity.Manufacturer;
import com.vehicle.exception.ManufacturerNotFoundException;
import com.vehicle.repository.ManufacturerRepository;
import com.vehicle.service.ManufacturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ManufacturerServiceImpl implements ManufacturerService  {

    @Autowired
    ManufacturerRepository manufacturerRepository;


    @Override
    public Manufacturer saveManufacturer(Manufacturer manufacturer) {
       return manufacturerRepository.save(manufacturer);
    }

    @Override
    public List<Manufacturer> fetchAllManufacturers() {
        List<Manufacturer> dbManufacturers = manufacturerRepository.findAll();
        return dbManufacturers;
    }

    @Override
    public Manufacturer getManufacturerForId(int id) {
        Optional<Manufacturer> dbManufacturer = manufacturerRepository.findById(id);
        if(dbManufacturer.isPresent()){
            return dbManufacturer.get();
        }
        return null;
    }

    @Override
    public Manufacturer updateManufacturer(int id, Manufacturer updatedManufacturer) {
        Manufacturer dbManufacturer = getManufacturerForId(id);
        if(dbManufacturer != null && Objects.nonNull(updatedManufacturer)){
            if(Objects.nonNull(updatedManufacturer.getManufacturerName()) && !"".equalsIgnoreCase(updatedManufacturer.getManufacturerName())){
                dbManufacturer.setManufacturerName(updatedManufacturer.getManufacturerName());
            }
            if(Objects.nonNull(updatedManufacturer.getCountryOfOrigin()) && !"".equalsIgnoreCase(updatedManufacturer.getCountryOfOrigin())){
                dbManufacturer.setCountryOfOrigin(updatedManufacturer.getCountryOfOrigin());
            }
            return manufacturerRepository.save(dbManufacturer);
        }

        return dbManufacturer;
    }

    @Override
    public void deleteManufacturerById(int id) throws ManufacturerNotFoundException {
        Manufacturer manufacturer = getManufacturerForId(id);
        if(manufacturer == null){
            throw new ManufacturerNotFoundException("Manufacturer NOT found for ID-"+id);
        }
        manufacturerRepository.deleteById(id);
    }


}
