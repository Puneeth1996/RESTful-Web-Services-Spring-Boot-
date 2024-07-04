package com.vehicle.details.service;

import com.vehicle.details.entity.VehicleDetail;
import com.vehicle.details.errors.VehicleNotSaved;

public interface VehicleDetailService {

    VehicleDetail saveVehicleDetails(VehicleDetail vehicleDetail) throws VehicleNotSaved;


}
