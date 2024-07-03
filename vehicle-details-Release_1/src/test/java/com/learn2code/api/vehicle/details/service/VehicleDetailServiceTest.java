package com.learn2code.api.vehicle.details.service;

import com.learn2code.api.vehicle.details.dao.VehicleDetailsDAO;
import com.learn2code.api.vehicle.details.entities.VehicleDetail;
import com.learn2code.api.vehicle.details.errors.VehicleDetailsNotFound;
import com.learn2code.api.vehicle.details.errors.VehicleNotSaved;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class VehicleDetailServiceTest {

    @MockBean
    private VehicleDetailsDAO vehicleDetailsDAO;

    @Autowired
    private VehicleDetailService vehicleDetailService;

    private VehicleDetail vd;

    @BeforeEach
    void setUp() {
        vd = new VehicleDetail();
        vd.setId(1);
        vd.setModelYear("2022");
        vd.setBrandName("Honda");
        vd.setModelName("Accord");
        vd.setTrimType("LS");
        vd.setBodyType("coupe");
        vd.setMiles(1200);
        vd.setPrice(25236.75);
        vd.setInterestRate(6.75);
        vd.setLocation("Seattle, WA");
        vd.setDescription("Excellent, pretty new, 3 years manufacturer warranty, amazing mileage.");
        vd.setSeller("Amazing Auto");
        vd.setSellerPhone("111-123-1234");
    }

    @Test
    @DisplayName("Test vehicle details saved when passed valid input data")
    void saveVehicleDetails() throws VehicleNotSaved {
        VehicleDetail input = new VehicleDetail();
        input.setModelYear("2022");
        input.setBrandName("Honda");
        input.setModelName("Accord");
        input.setTrimType("LS");
        input.setBodyType("coupe");
        input.setMiles(1200);
        input.setPrice(25236.75);
        input.setInterestRate(6.75);
        input.setLocation("Seattle, WA");
        input.setDescription("Excellent, pretty new, 3 years manufacturer warranty, amazing mileage.");
        input.setSeller("Amazing Auto");
        input.setSellerPhone("111-123-1234");

        Mockito.when(vehicleDetailsDAO.save(input)).thenReturn(vd);
        VehicleDetail output = vehicleDetailService.saveVehicleDetails(input);
        assertEquals(output.getId(),vd.getId());
        assertEquals(output.getBrandName(), vd.getBrandName());
        assertEquals(output.getSeller(),vd.getSeller());
    }

    @Test
    @DisplayName("Get All vehicles from database test")
    public void fetchAllVehicleDetails() throws VehicleDetailsNotFound {
        List<VehicleDetail> output = Arrays.asList(
                new VehicleDetail(1,"2022","Toyota","Corolla","L","",21000.0,2500,4.69,"York, PA","Clean and efficient car","T-Auto","321-111-2323"),
                new VehicleDetail(2,"2022","Honda","Civic","EX","",23000.0,1500,4.69,"York, PA","Clean and efficient car","T-Auto","321-111-2323"),
                new VehicleDetail(3,"2021","Toyota","Camry","LS","",24500.0,2300,5.69,"Erie, PA","Clean and efficient car","D-Auto","211-222-2323")
        );
        Mockito.when(vehicleDetailsDAO.findAll()).thenReturn(output);

        List<VehicleDetail> dbList = vehicleDetailService.fetchAllVehicleDetails();

        assertEquals(dbList.size(),4);
        assertEquals(dbList.get(0).getBrandName(),"Toyota");
        assertEquals(dbList.get(1).getBrandName(),"Honda");
    }

    @Test
    public void testGetVehicleById() throws VehicleDetailsNotFound {
        VehicleDetail dbVehicleDetail = new VehicleDetail(1,"2022","Toyota","Corolla","L","",21000.0,2500,4.69,"York, PA","Clean and efficient car","T-Auto","321-111-2323");
        Mockito.when(vehicleDetailsDAO.findById(1)).thenReturn(java.util.Optional.of(dbVehicleDetail));
        VehicleDetail savedVehicleDetail = vehicleDetailService.getVehicleById(1);
        assertEquals(1,savedVehicleDetail.getId());
        assertEquals("Toyota",savedVehicleDetail.getBrandName());
    }
}