package com.vehicle.service.impl;

import com.vehicle.entity.VehicleMarketPrice;
import com.vehicle.repository.VehicleMarketPriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VehicleMarketPriceServiceImpl {
    @Autowired
    private VehicleMarketPriceRepository vehicleMarketPriceRepository;

    public VehicleMarketPrice saveVehicleMarketPrice(VehicleMarketPrice vehicleMarketPrice){
        return vehicleMarketPriceRepository.save(vehicleMarketPrice);
    }

    public VehicleMarketPrice getVehicleMarketPriceByBrandModel(String brandName,String modelName){
        return vehicleMarketPriceRepository.findByBrandNameAndModelName(brandName,modelName);
    }
}
