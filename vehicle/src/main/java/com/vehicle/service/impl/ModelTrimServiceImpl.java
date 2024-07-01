package com.vehicle.service.impl;

import com.vehicle.entity.Model;
import com.vehicle.entity.TrimType;
import com.vehicle.exception.ModelNotFoundException;
import com.vehicle.exception.TrimTypeNotFoundException;
import com.vehicle.repository.ModelRepository;
import com.vehicle.repository.TrimTypeRepository;
import com.vehicle.service.ModelTrimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ModelTrimServiceImpl implements ModelTrimService {

    @Autowired
    ModelRepository modelRepository;

    @Autowired
    TrimTypeRepository trimTypeRepository;

    @Override
    public Model saveModel(Model model) {
        Model savedModel = modelRepository.save(model);
        return savedModel;
    }

    @Override
    public TrimType saveTrimType(TrimType trimType) {
        return trimTypeRepository.save(trimType);
    }

    @Override
    public List<Model> getAllModels() {
        List<Model> savedModels = modelRepository.findAll();
        return savedModels;
    }


    @Override
    public Model modifyModel(int id, Model model) throws ModelNotFoundException {
        Model dbModel = getModelById(id);
        if(dbModel != null && Objects.nonNull(model)){
            if(Objects.nonNull(model.getModelName()) && !"".equalsIgnoreCase(model.getModelName())){
                dbModel.setModelName(model.getModelName());
            }
            dbModel = modelRepository.save(dbModel);
        }
        return dbModel;
    }

    @Override
    public TrimType modifyTrimType(int id, TrimType trimType) throws TrimTypeNotFoundException {
        TrimType dbTrimType = getTrimTypeById(id);
        if(dbTrimType != null && Objects.nonNull(trimType)){
            if(Objects.nonNull(trimType.getTrimType()) && !"".equalsIgnoreCase(trimType.getTrimType())){
                dbTrimType.setTrimType(trimType.getTrimType());
            }
            dbTrimType = trimTypeRepository.save(dbTrimType);
        }
        return dbTrimType;
    }

    @Override
    public Model getModelById(int id) throws ModelNotFoundException {
        Optional<Model> dbModelOptional = modelRepository.findById(id);
        if(!dbModelOptional.isPresent()){
            throw new ModelNotFoundException("No Model found in DB with ID-"+id);
        }
        return dbModelOptional.get();
    }

    @Override
    public TrimType getTrimTypeById(int id) throws TrimTypeNotFoundException {
        Optional<TrimType> trimTypeOptional = trimTypeRepository.findById(id);

        if(!trimTypeOptional.isPresent()){
            throw new TrimTypeNotFoundException("No Trim Type found in DB with ID-"+id);
        }
        return trimTypeOptional.get();
    }




}
