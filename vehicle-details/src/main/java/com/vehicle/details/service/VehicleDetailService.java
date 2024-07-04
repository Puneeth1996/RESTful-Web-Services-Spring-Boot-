package com.vehicle.details.service;

import com.vehicle.details.entity.VehicleDetail;
import com.vehicle.details.errors.VehicleDetailsNotFound;
import com.vehicle.details.errors.VehicleNotSaved;

import java.util.List;

public interface VehicleDetailService {

    VehicleDetail saveVehicleDetails(VehicleDetail vehicleDetail) throws VehicleNotSaved;
    List<VehicleDetail> fetchAllVehicleDetails() throws VehicleDetailsNotFound;

}
