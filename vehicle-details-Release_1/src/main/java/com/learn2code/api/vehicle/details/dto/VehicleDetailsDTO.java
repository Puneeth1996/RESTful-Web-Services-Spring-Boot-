package com.learn2code.api.vehicle.details.dto;

import com.learn2code.api.vehicle.details.entities.VehicleDetail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleDetailsDTO {
    List<VehicleDetail> vehicleDetailList;
}
