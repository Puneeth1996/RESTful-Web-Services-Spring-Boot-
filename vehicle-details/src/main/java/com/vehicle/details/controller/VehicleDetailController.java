package com.vehicle.details.controller;


import com.vehicle.details.entity.VehicleDetail;
import com.vehicle.details.service.VehicleDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/vehicle-details")
public class VehicleDetailController {


    @Autowired
    VehicleDetailService vehicleDetailService;


    @PostMapping
    public ResponseEntity<VehicleDetail> saveVehicleDetails(@RequestBody VehicleDetail vehicleDetail){
        VehicleDetail dbVehicle = vehicleDetailService.saveVehicleDetails(vehicleDetail);
        return new ResponseEntity<>(dbVehicle, HttpStatus.CREATED);
    }



}
