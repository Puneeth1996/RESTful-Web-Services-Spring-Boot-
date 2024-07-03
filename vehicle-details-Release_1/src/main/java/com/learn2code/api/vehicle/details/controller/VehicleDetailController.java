package com.learn2code.api.vehicle.details.controller;

import com.learn2code.api.vehicle.details.dto.VehicleDetailsDTO;
import com.learn2code.api.vehicle.details.entities.VehicleDetail;
import com.learn2code.api.vehicle.details.errors.MandatoryFieldsMissingException;
import com.learn2code.api.vehicle.details.errors.VehicleDetailsNotFound;
import com.learn2code.api.vehicle.details.service.VehicleDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/vehicle-details")
public class VehicleDetailController {
    @Autowired
    private VehicleDetailService vehicleDetailService;

    /*@GetMapping
    public String hello(){
        return "Hello";
    }*/
    @PostMapping
    public ResponseEntity<VehicleDetail> saveVehicleDetails(@Valid @RequestBody VehicleDetail vehicleDetail, BindingResult result) throws Exception {
        if(result.hasErrors()){
            List<ObjectError> errorList = result.getAllErrors();
            String allErrors = "";
            for(ObjectError err:errorList){
                allErrors +=err.getDefaultMessage()+",";
            }
            throw new MandatoryFieldsMissingException(allErrors);
        }

        VehicleDetail dbVehicle = vehicleDetailService.saveVehicleDetails(vehicleDetail);
        return new ResponseEntity<>(dbVehicle, HttpStatus.CREATED);
    }

    @GetMapping
    public VehicleDetailsDTO getAllVehicleDetails() throws VehicleDetailsNotFound {
        List<VehicleDetail> savedVehicles = vehicleDetailService.fetchAllVehicleDetails();
        return new VehicleDetailsDTO(savedVehicles);
    }

    @GetMapping("/{vehicleId}")
    public VehicleDetail getVehicleDetailById(@PathVariable int vehicleId) throws VehicleDetailsNotFound {
        return vehicleDetailService.getVehicleById(vehicleId);
    }

    @DeleteMapping("/{vehicleId}")
    public ResponseEntity<String> deleteVehicleDetailsById(@PathVariable int vehicleId) throws VehicleDetailsNotFound {
        vehicleDetailService.deleteVehicleDetailsById(vehicleId);
        return new ResponseEntity<>("Deleted vehicle details from DB with ID-"+vehicleId,HttpStatus.OK);
    }

    @PutMapping("/{vehicleId}")
    public ResponseEntity<VehicleDetail> updateVehicleBYiD(@PathVariable int vehicleId,@RequestBody VehicleDetail vehicleDetail) throws VehicleDetailsNotFound {
        VehicleDetail savedVehicle = vehicleDetailService.updateVehicleDetails(vehicleId,vehicleDetail);
        return ResponseEntity.status(HttpStatus.OK).body(savedVehicle);
    }

    @GetMapping("/search")
    public VehicleDetailsDTO getVehiclesByCrietaria(@RequestParam String modelYear,@RequestParam String brand,@RequestParam String model,@RequestParam String trim,@RequestParam String price) throws VehicleDetailsNotFound {
        if(price =="" || price==null){
            price="0.0";
        }
        List<VehicleDetail> filteredVehicles = vehicleDetailService.fetchFilteredVehiclesDetails(modelYear,brand,model,trim,Double.parseDouble(price));
        return new VehicleDetailsDTO(filteredVehicles);
    }
}
