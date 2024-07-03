package com.learn2code.api.vehicle.details.service.impl;

import com.learn2code.api.vehicle.details.dao.VehicleDetailsDAO;
import com.learn2code.api.vehicle.details.entities.VehicleDetail;
import com.learn2code.api.vehicle.details.errors.VehicleDetailsNotFound;
import com.learn2code.api.vehicle.details.errors.VehicleNotSaved;
import com.learn2code.api.vehicle.details.service.VehicleDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class VehicleDetailServiceImpl implements VehicleDetailService {
    @Autowired
    private VehicleDetailsDAO vehicleDetailsDAO;

    @Override
    public VehicleDetail saveVehicleDetails(VehicleDetail vehicleDetail) throws VehicleNotSaved {
        VehicleDetail dbVehicle = null;
        try {
            dbVehicle = vehicleDetailsDAO.save(vehicleDetail);
        } catch (Exception e){
            throw new VehicleNotSaved("Unable to save vehicle in DB. Got error:- "+e.getMessage());
        }
        return dbVehicle;
    }

    @Override
    public List<VehicleDetail> fetchAllVehicleDetails() throws VehicleDetailsNotFound {
        List<VehicleDetail> dbVehicles = vehicleDetailsDAO.findAll();
        if(dbVehicles.size()==0){
            throw new VehicleDetailsNotFound("No vehicle details found in Database!");
        }
        return dbVehicles;
    }

    @Override
    public VehicleDetail getVehicleById(int vehicleId) throws VehicleDetailsNotFound {
        Optional<VehicleDetail> optionalVehicleDetail = vehicleDetailsDAO.findById(vehicleId);
        if(!optionalVehicleDetail.isPresent()){
            throw new VehicleDetailsNotFound("No vehicle details found in database for vehicle ID-"+vehicleId);
        }
        return optionalVehicleDetail.get();
    }

    @Override
    public void deleteVehicleDetailsById(int vehicleId) throws VehicleDetailsNotFound {
        Optional<VehicleDetail> optionalVehicleDetail = vehicleDetailsDAO.findById(vehicleId);
        if(!optionalVehicleDetail.isPresent()){
            throw new VehicleDetailsNotFound("No vehicle details found in database for vehicle ID-"+vehicleId);
        }
        vehicleDetailsDAO.deleteById(vehicleId);
    }

    @Override
    public VehicleDetail updateVehicleDetails(int vehicleId,VehicleDetail vehicleDetail) throws VehicleDetailsNotFound {
        Optional<VehicleDetail> optionalVehicleDetail = vehicleDetailsDAO.findById(vehicleId);
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

        return vehicleDetailsDAO.save(dbVehcile);
    }

    @Override
    public List<VehicleDetail> fetchFilteredVehiclesDetails(String modelYear, String brand, String model, String trim, double price) throws VehicleDetailsNotFound {
        List<VehicleDetail> vehicleDetailList = null;
        if(modelYear!="" && brand!="" && model!="" && trim!="" && price>0.0){
            vehicleDetailList = vehicleDetailsDAO.filterVehicleBasedOnCriteria(modelYear,brand,model,trim,price);
        } else if(modelYear!="" && brand!="" && model!="" && trim!=""){
            vehicleDetailList = vehicleDetailsDAO.filterVehicleBasedOnCriteria5(modelYear,brand,model,trim);
        } else if(modelYear!="" && brand!="" && model!="" && price>0.0) {
            vehicleDetailList = vehicleDetailsDAO.filterVehicleBasedOnCriteria6(modelYear,brand,model,price);
        } else if(modelYear!="" && brand!="" && price>0.0) {
            vehicleDetailList = vehicleDetailsDAO.filterVehicleBasedOnCriteria6(modelYear,brand,price);
        } else if(brand!="" && model!="" && trim!="" && price>0.0){
            vehicleDetailList = vehicleDetailsDAO.filterVehicleBasedOnCriteria(brand,model,trim,price);
        } else if(brand!="" && model!="" && trim!=""){
            vehicleDetailList = vehicleDetailsDAO.filterVehicleBasedOnCriteria1(brand,model,trim);
        } else if(brand!="" && model!="" && price>0.0){
            vehicleDetailList = vehicleDetailsDAO.filterVehicleBasedOnCriteria2(brand,model,price);
        } else if(modelYear!="" && price>0.0){
            vehicleDetailList = vehicleDetailsDAO.filterVehicleBasedOnCriteria7(modelYear,price);
        } else if(brand!="" && price>0.0){
            vehicleDetailList = vehicleDetailsDAO.filterVehicleBasedOnCriteria2(brand,price);
        } else if(brand!=""){
            vehicleDetailList = vehicleDetailsDAO.filterVehicleBasedOnCriteria3(brand);
        } else if(price>0.0){
            vehicleDetailList = vehicleDetailsDAO.filterVehicleBasedOnCriteria4(price);
        } else if(modelYear!=""){
            vehicleDetailList = vehicleDetailsDAO.filterVehicleBasedOnCriteria8(modelYear);
        } else {
            vehicleDetailList = fetchAllVehicleDetails();
        }
        return vehicleDetailList;
    }

}
