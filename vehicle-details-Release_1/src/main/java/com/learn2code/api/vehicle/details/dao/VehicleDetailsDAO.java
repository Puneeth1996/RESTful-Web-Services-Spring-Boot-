package com.learn2code.api.vehicle.details.dao;

import com.learn2code.api.vehicle.details.entities.VehicleDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VehicleDetailsDAO extends JpaRepository<VehicleDetail,Integer> {
    @Query(value = "SELECT * FROM vehicle_details WHERE model_year=?1 AND brand_name=?2 AND model_name=?3 AND trim_type=?4 AND price<=?5",nativeQuery = true)
    List<VehicleDetail> filterVehicleBasedOnCriteria(String modelYear, String brand, String model, String trim, double price);

    @Query(value = "SELECT * FROM vehicle_details WHERE brand_name=?1 AND model_name=?2 AND trim_type=?3 AND price<=?4",nativeQuery = true)
    List<VehicleDetail> filterVehicleBasedOnCriteria(String brand, String model, String trim, double price);

    @Query(value = "SELECT * FROM vehicle_details WHERE brand_name=?1 AND model_name=?2 AND trim_type=?3",nativeQuery = true)
    List<VehicleDetail> filterVehicleBasedOnCriteria1(String brand, String model, String trim);

    @Query(value = "SELECT * FROM vehicle_details WHERE brand_name=?1 AND model_name=?2 AND price<=?3",nativeQuery = true)
    List<VehicleDetail> filterVehicleBasedOnCriteria2(String brand, String model, double price);

    @Query(value = "SELECT * FROM vehicle_details WHERE brand_name=?1 AND price<=?2",nativeQuery = true)
    List<VehicleDetail> filterVehicleBasedOnCriteria2(String brand, double price);

    @Query(value = "SELECT * FROM vehicle_details WHERE brand_name=?1",nativeQuery = true)
    List<VehicleDetail> filterVehicleBasedOnCriteria3(String brand);

    @Query(value = "SELECT * FROM vehicle_details WHERE price<=?1",nativeQuery = true)
    List<VehicleDetail> filterVehicleBasedOnCriteria4(double price);

    @Query(value = "SELECT * FROM vehicle_details WHERE model_year=?1 AND brand_name=?2 AND model_name=?3 AND trim_type=?4",nativeQuery = true)
    List<VehicleDetail> filterVehicleBasedOnCriteria5(String modelYear, String brand, String model, String trim);

    @Query(value = "SELECT * FROM vehicle_details WHERE model_year=?1 AND brand_name=?2 AND model_name=?3 AND price<=?4",nativeQuery = true)
    List<VehicleDetail> filterVehicleBasedOnCriteria6(String modelYear, String brand, String model, double price);

    @Query(value = "SELECT * FROM vehicle_details WHERE model_year=?1 AND brand_name=?2 AND price<=?3",nativeQuery = true)
    List<VehicleDetail> filterVehicleBasedOnCriteria6(String modelYear, String brand, double price);

    @Query(value = "SELECT * FROM vehicle_details WHERE model_year=?1 AND price<=?2",nativeQuery = true)
    List<VehicleDetail> filterVehicleBasedOnCriteria7(String modelYear, double price);

    @Query(value = "SELECT * FROM vehicle_details WHERE model_year=?1",nativeQuery = true)
    List<VehicleDetail> filterVehicleBasedOnCriteria8(String modelYear);
}
