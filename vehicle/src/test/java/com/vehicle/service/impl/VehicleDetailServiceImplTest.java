package com.vehicle.service.impl;

import com.vehicle.dto.ClientVehicleDetail;
import com.vehicle.service.VehicleDetailService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class VehicleDetailServiceImplTest {

    @Autowired
    private VehicleDetailService vehicleDetailService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testGetAllVehicleDetails() {
        List<ClientVehicleDetail> allClientVehicles = vehicleDetailService.getAllVehicleDetails();
        assertEquals(allClientVehicles.get(0).getEstimatedMonthly(), "$538.02311875 / monthly est.");
        assertEquals(allClientVehicles.get(1).getDealType(),"Great Deal");
        assertEquals(allClientVehicles.get(2).getAmountBelowMarket(),"$1102.7750000000015 below market price.");
    }
}