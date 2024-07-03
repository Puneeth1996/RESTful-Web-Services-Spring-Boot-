package com.learn2code.api.vehicle.details.service;

import com.learn2code.api.vehicle.details.entities.VehicleDetail;
import com.learn2code.api.vehicle.details.errors.VehicleDetailsNotFound;
import com.learn2code.api.vehicle.details.errors.VehicleNotSaved;

import java.util.List;

public interface VehicleDetailService {
    VehicleDetail saveVehicleDetails(VehicleDetail vehicleDetail) throws VehicleNotSaved;
    List<VehicleDetail> fetchAllVehicleDetails() throws VehicleDetailsNotFound;
    VehicleDetail getVehicleById(int vehicleId) throws VehicleDetailsNotFound;
    void deleteVehicleDetailsById(int vehicleId) throws VehicleDetailsNotFound;
    VehicleDetail updateVehicleDetails(int vehicleId, VehicleDetail vehicleDetail) throws VehicleDetailsNotFound;
    List<VehicleDetail> fetchFilteredVehiclesDetails(String modelYear,String brand,String model,String trim,double price) throws VehicleDetailsNotFound;
}
