package com.vehicle.service;

import com.vehicle.entity.Model;
import com.vehicle.entity.TrimType;
import com.vehicle.exception.ManufacturerNotFoundException;
import com.vehicle.exception.ModelNotFoundException;
import com.vehicle.exception.TrimTypeNotFoundException;

import java.util.List;

public interface ModelTrimService {
    Model saveModel(Model model);
    TrimType saveTrimType(TrimType trimType);
    List<Model> getAllModels();
    Model modifyModel(int id, Model model) throws ModelNotFoundException;
    TrimType modifyTrimType(int id, TrimType trimType) throws TrimTypeNotFoundException;
    Model getModelById(int id) throws ModelNotFoundException;
    TrimType getTrimTypeById(int id) throws TrimTypeNotFoundException;
    void deleteModelById(int id) throws ModelNotFoundException;
    void deleteTrimType(int id) throws TrimTypeNotFoundException;
    List<Model> getModelsByManufacturerId(int manufacturerId) throws ManufacturerNotFoundException;
    List<Model> getModelsByManufacturerName(String name) throws ManufacturerNotFoundException;
}
