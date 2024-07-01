package com.vehicle.controller;

import com.vehicle.entity.Model;
import com.vehicle.entity.TrimType;
import com.vehicle.service.ModelTrimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/model-trim")
public class ModelTrimController {

    @Autowired
    ModelTrimService modelTrimService;


    @PostMapping
    public ResponseEntity<Model> createModelTrim(@RequestBody Model model){
        Model savedRecord = modelTrimService.saveModel(model);
        return new ResponseEntity<>(savedRecord, HttpStatus.CREATED);
    }

    @PostMapping("/trim-type")
    public ResponseEntity<TrimType> createTrimType(@RequestBody TrimType trimType){
        TrimType savedTrim = modelTrimService.saveTrimType(trimType);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTrim);
    }


}
