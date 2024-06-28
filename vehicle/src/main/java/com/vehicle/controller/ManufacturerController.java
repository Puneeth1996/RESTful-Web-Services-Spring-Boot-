package com.vehicle.controller;

import com.vehicle.entity.Manufacturer;
import com.vehicle.exception.ManufacturerNotFoundException;
import com.vehicle.exception.MissingFieldException;
import com.vehicle.service.ManufacturerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/manufacturers")
public class ManufacturerController {

    @Autowired
    ManufacturerService manufacturerService;

    @PostMapping
    public ResponseEntity<Manufacturer> createManufacturerInDB(@RequestBody Manufacturer manufacturer) {
        return new ResponseEntity<>(manufacturerService.saveManufacturer(manufacturer), HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<List<Manufacturer>> getAllManufacturers(){
        List<Manufacturer> savedManufacturers = manufacturerService.fetchAllManufacturers();
        return ResponseEntity.status(HttpStatus.OK).body(savedManufacturers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Manufacturer> getManufacturerBasedOnId(@PathVariable int id) throws ManufacturerNotFoundException {
        Manufacturer dbManufacturer = manufacturerService.getManufacturerForId(id);
        if(dbManufacturer == null){
            throw new ManufacturerNotFoundException("No manufacturer found for ID-"+id);
        }
        return ResponseEntity.ok(dbManufacturer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Manufacturer> updateManufacture(@PathVariable int id, @Valid @RequestBody Manufacturer manufacturer, BindingResult result) throws Exception {
        if(result.hasErrors()){
            List<ObjectError> errors = result.getAllErrors();
            throw new MissingFieldException(errors.get(0).getDefaultMessage());
        }
        Manufacturer updatedManufacturer = manufacturerService.updateManufacturer(id,manufacturer);
        if(updatedManufacturer == null){
            throw new ManufacturerNotFoundException("No manufacturer found for ID-"+id);
        }
        return new ResponseEntity<>(updatedManufacturer,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteManufacturerById(@PathVariable int id) throws ManufacturerNotFoundException {
        manufacturerService.deleteManufacturerById(id);
        return new ResponseEntity<>("Manufacturer DELETED from DB with ID-"+id,HttpStatus.OK);
    }


}
