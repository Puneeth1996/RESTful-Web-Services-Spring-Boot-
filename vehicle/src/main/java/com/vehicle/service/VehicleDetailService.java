package com.vehicle.service;


import com.vehicle.dto.ClientVehicleDetail;
import com.vehicle.entity.VehicleDetail;
import com.vehicle.exception.VehicleDetailsNotFound;

import java.util.List;

public interface VehicleDetailService {
    List<ClientVehicleDetail> getAllVehicleDetails();
    VehicleDetail getVehicleById(int vehicleId) throws VehicleDetailsNotFound;
}