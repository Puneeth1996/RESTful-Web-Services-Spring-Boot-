package com.vehicle.controller;

import com.vehicle.entity.VehicleMarketPrice;
import com.vehicle.service.impl.VehicleMarketPriceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/vechicle-market-price")
public class VehicleMarketPriceController {

    @Autowired
    private VehicleMarketPriceServiceImpl vehicleMarketPriceService;

    @PostMapping
    public ResponseEntity<VehicleMarketPrice> saveVehicleMarketPrice(@RequestBody VehicleMarketPrice vehicleMarketPrice){
        return ResponseEntity.status(HttpStatus.CREATED).body(vehicleMarketPriceService.saveVehicleMarketPrice(vehicleMarketPrice));
    }
}