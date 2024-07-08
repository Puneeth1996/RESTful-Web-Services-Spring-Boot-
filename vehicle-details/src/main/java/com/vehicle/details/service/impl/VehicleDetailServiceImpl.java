package com.vehicle.details.service.impl;

import com.vehicle.details.entity.VehicleDetail;
import com.vehicle.details.errors.VehicleDetailsNotFound;
import com.vehicle.details.errors.VehicleNotSaved;
import com.vehicle.details.repository.VehicleDetailsRepository;
import com.vehicle.details.service.VehicleDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;


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


    @Override
    public VehicleDetail getVehicleById(int vehicleId) throws VehicleDetailsNotFound {
        Optional<VehicleDetail> optionalVehicleDetail = vehicleDetailsRepository.findById(vehicleId);
        if(!optionalVehicleDetail.isPresent()){
            throw new VehicleDetailsNotFound("No vehicle details found in database for vehicle ID-"+vehicleId);
        }
        return optionalVehicleDetail.get();
    }


    @Override
    public void deleteVehicleDetailsById(int vehicleId) throws VehicleDetailsNotFound {
        Optional<VehicleDetail> optionalVehicleDetail = vehicleDetailsRepository.findById(vehicleId);
        if(!optionalVehicleDetail.isPresent()){
            throw new VehicleDetailsNotFound("No vehicle details found in database for vehicle ID-"+vehicleId);
        }
        vehicleDetailsRepository.deleteById(vehicleId);
    }



    @Override
    public VehicleDetail updateVehicleDetails(int vehicleId,VehicleDetail vehicleDetail) throws VehicleDetailsNotFound {
        Optional<VehicleDetail> optionalVehicleDetail = vehicleDetailsRepository.findById(vehicleId);
        if(!optionalVehicleDetail.isPresent()){
            throw new VehicleDetailsNotFound("No vehicle found in DB for ID-"+vehicleId);
        }
        VehicleDetail dbVehcile = optionalVehicleDetail.get();
        if(vehicleDetail.getModelYear()!="" && Objects.nonNull(vehicleDetail.getModelYear())){
            dbVehcile.setModelYear(vehicleDetail.getModelYear());
        }
        if(vehicleDetail.getBrandName()!="" && Objects.nonNull(vehicleDetail.getBrandName())){
            dbVehcile.setBrandName(vehicleDetail.getBrandName());
        }
        if(vehicleDetail.getModelName()!="" && Objects.nonNull(vehicleDetail.getModelName())){
            dbVehcile.setModelName(vehicleDetail.getModelName());
        }
        if(vehicleDetail.getPrice()!=0.0 && Objects.nonNull(vehicleDetail.getPrice())){
            dbVehcile.setPrice(vehicleDetail.getPrice());
        }
        if(vehicleDetail.getMiles()!=0 && Objects.nonNull(vehicleDetail.getMiles())){
            dbVehcile.setMiles(vehicleDetail.getMiles());
        }
        if(vehicleDetail.getInterestRate()!=0.0 && Objects.nonNull(vehicleDetail.getInterestRate())){
            dbVehcile.setInterestRate(vehicleDetail.getInterestRate());
        }
        if(vehicleDetail.getSeller()!="" && Objects.nonNull(vehicleDetail.getSeller())){
            dbVehcile.setSeller(vehicleDetail.getSeller());
        }
        if(vehicleDetail.getSellerPhone()!="" && Objects.nonNull(vehicleDetail.getSellerPhone())){
            dbVehcile.setSellerPhone(vehicleDetail.getSellerPhone());
        }
        dbVehcile.setTrimType(vehicleDetail.getTrimType());
        dbVehcile.setBodyType(vehicleDetail.getBodyType());
        dbVehcile.setLocation(vehicleDetail.getLocation());
        dbVehcile.setDescription(vehicleDetail.getDescription());

        return vehicleDetailsRepository.save(dbVehcile);
    }




}
