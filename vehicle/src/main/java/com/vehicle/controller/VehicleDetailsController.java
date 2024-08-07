package com.vehicle.controller;

import com.vehicle.dto.ClientVehicleDetail;
import com.vehicle.entity.VehicleDetail;
import com.vehicle.entity.VehicleDetailsDTO;
import com.vehicle.exception.VehicleDetailsNotFound;
import com.vehicle.service.VehicleDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


    @GetMapping("/search")
    public ResponseEntity<List<ClientVehicleDetail>> searchVehicleByFilterCriteria(@RequestParam String modelYear, @RequestParam String brand, @RequestParam String model, @RequestParam String trim, @RequestParam String price){
        List<ClientVehicleDetail> filteredVehicles = vehicleDetailService.fetchVehicleDetailByCriteria(modelYear,brand,model,trim,price);
        return new ResponseEntity<>(filteredVehicles,HttpStatus.OK);
    }


}
