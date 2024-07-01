package com.vehicle.service.impl;

import com.vehicle.entity.Model;
import com.vehicle.entity.TrimType;
import com.vehicle.repository.ModelRepository;
import com.vehicle.repository.TrimTypeRepository;
import com.vehicle.service.ModelTrimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
