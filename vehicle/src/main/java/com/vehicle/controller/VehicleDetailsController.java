package com.vehicle.controller;

import com.vehicle.dto.ClientVehicleDetail;
import com.vehicle.entity.VehicleDetail;
import com.vehicle.entity.VehicleDetailsDTO;
import com.vehicle.exception.VehicleDetailsNotFound;
import com.vehicle.service.VehicleDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/vehicle-details")
public class VehicleDetailsController {



    @Autowired
    private VehicleDetailService vehicleDetailService;

    @GetMapping
    public ResponseEntity<List<ClientVehicleDetail>> getAllVehicleDetails(){
        List<ClientVehicleDetail> dbVehicles = vehicleDetailService.getAllVehicleDetails();
        return new ResponseEntity<>(dbVehicles, HttpStatus.OK);
    }

    @GetMapping("/{vehicleId}")
    public ResponseEntity<VehicleDetail> fetchVehicleById(@PathVariable int vehicleId) throws VehicleDetailsNotFound {
        return ResponseEntity.ok(vehicleDetailService.getVehicleById(vehicleId));
    }


}
