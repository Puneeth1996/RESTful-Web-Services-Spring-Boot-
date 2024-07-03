package com.learn2code.api.vehicle.details.controller;

import com.learn2code.api.vehicle.details.entities.VehicleDetail;
import com.learn2code.api.vehicle.details.errors.VehicleDetailsNotFound;
import com.learn2code.api.vehicle.details.service.VehicleDetailService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@WebMvcTest(VehicleDetailController.class)
class VehicleDetailControllerTest {

    @MockBean
    private VehicleDetailService vehicleDetailService;

    @Autowired
    private MockMvc mockMvc;

    private VehicleDetail output;
    @BeforeEach
    void setUp() {
        output = new VehicleDetail();
        output.setBrandName("Honda");
        output.setModelYear("2021");
        output.setModelName("Civic");
        output.setTrimType("EX");
        output.setPrice(23260.99);
        output.setMiles(23000);
        output.setInterestRate(5.35);
        output.setDescription("Nice and clean car with excellent mileage!");
        output.setLocation("Jersy City, NJ");
        output.setSeller("Unique Auto Sales");
        output.setSellerPhone("123-231-2341");
    }

    @Test
    @DisplayName("Test save vehicle details controller endpoint")
    void saveVehicleDetails() throws Exception {
        VehicleDetail input = new VehicleDetail();
        input.setBrandName("Honda");
        input.setModelYear("2021");
        input.setModelName("Civic");
        input.setTrimType("EX");
        input.setPrice(23260.99);
        input.setMiles(23000);
        input.setInterestRate(5.35);
        input.setDescription("Nice and clean car with excellent mileage!");
        input.setLocation("Jersy City, NJ");
        input.setSeller("Unique Auto Sales");
        input.setSellerPhone("123-231-2341");

        Mockito.when(vehicleDetailService.saveVehicleDetails(input)).thenReturn(output);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/vehicle-details")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\n" +
                "    \"modelYear\": 2021,\n" +
                "    \"brandName\":\"Honda\",\n" +
                "    \"modelName\":\"Civic\",\n" +
                "    \"trimType\":\"EX\",\n" +
                "    \"bodyType\":\"\",\n" +
                "    \"price\": 23260.99,\n" +
                "    \"miles\": 23000,\n" +
                "    \"interestRate\": 5.35,\n" +
                "    \"location\": \"Jersy City, NJ\",\n" +
                "    \"description\":\"Nice and clean car with excellent mileage!\",\n" +
                "    \"seller\":\"Unique Auto Sales\",\n" +
                "    \"sellerPhone\": \"123-231-2341\"\n" +
                "}")).andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void testGetAllVehicles() throws Exception {
        List<VehicleDetail> output = Arrays.asList(
                new VehicleDetail(1,"2022","Toyota","Corolla","L","",21000.0,2500,4.69,"York, PA","Clean and efficient car","T-Auto","321-111-2323"),
                new VehicleDetail(2,"2022","Honda","Civic","EX","",23000.0,1500,4.69,"York, PA","Clean and efficient car","T-Auto","321-111-2323"),
                new VehicleDetail(3,"2021","Toyota","Camry","LS","",24500.0,2300,5.69,"Erie, PA","Clean and efficient car","D-Auto","211-222-2323")
        );
        Mockito.when(vehicleDetailService.fetchAllVehicleDetails()).thenReturn(output);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/vehicle-details")
        .contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(3))
        .andExpect(MockMvcResultMatchers.jsonPath("$[1].brandName").value("GM"));
    }
}