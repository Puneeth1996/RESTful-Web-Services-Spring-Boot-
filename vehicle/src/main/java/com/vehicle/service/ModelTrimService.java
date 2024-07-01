package com.vehicle.service;

import com.vehicle.entity.Model;
import com.vehicle.entity.TrimType;

public interface ModelTrimService {
    Model saveModel(Model model);
    TrimType saveTrimType(TrimType trimType);
}
