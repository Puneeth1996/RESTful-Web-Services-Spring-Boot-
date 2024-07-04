package com.vehicle.details.service.impl;

import com.vehicle.details.entity.VehicleDetail;
import com.vehicle.details.errors.VehicleDetailsNotFound;
import com.vehicle.details.errors.VehicleNotSaved;
import com.vehicle.details.repository.VehicleDetailsRepository;
import com.vehicle.details.service.VehicleDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class VehicleDetailServiceImpl implements VehicleDetailService {

    @Autowired
    VehicleDetailsRepository vehicleDetailsRepository;


    @Override
    public VehicleDetail saveVehicleDetails(VehicleDetail vehicleDetail) throws VehicleNotSaved {
        VehicleDetail dbVehicle = null;
        try {
            dbVehicle = vehicleDetailsRepository.save(vehicleDetail);
        } catch (Exception e){
            throw new VehicleNotSaved("Unable to save vehicle in DB. Got error:- "+e.getMessage());
        }
        return dbVehicle;
    }



    @Override
    public List<VehicleDetail> fetchAllVehicleDetails() throws VehicleDetailsNotFound {
        List<VehicleDetail> dbVehicles = vehicleDetailsRepository.findAll();
        if(dbVehicles.size()==0){
            throw new VehicleDetailsNotFound("No vehicle details found in Database!");
        }
        return dbVehicles;
    }


}
