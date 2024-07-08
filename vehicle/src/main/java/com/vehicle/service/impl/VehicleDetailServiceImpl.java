package com.vehicle.service.impl;

import com.vehicle.dto.ClientVehicleDetail;
import com.vehicle.entity.VehicleDetail;
import com.vehicle.entity.VehicleDetailsDTO;
import com.vehicle.entity.VehicleMarketPrice;
import com.vehicle.service.VehicleDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class VehicleDetailServiceImpl implements VehicleDetailService {

    @Autowired
    public RestTemplate restTemplate;

    private VehicleMarketPriceServiceImpl vehicleMarketPriceService;

    @Override
    public List<ClientVehicleDetail> getAllVehicleDetails() {
        List<ClientVehicleDetail> clientVehicleDetailsList = new ArrayList<>();
        ClientVehicleDetail clientVehicleDetail = null;
        VehicleDetailsDTO vehicleDetailsDTO = restTemplate.getForObject("http://localhost:9192/api/v1/vehicle-details",VehicleDetailsDTO.class);
        for(VehicleDetail vehicleDetail :vehicleDetailsDTO.getVehicleDetailList()){
            clientVehicleDetail = new ClientVehicleDetail();
            clientVehicleDetail.setId(vehicleDetail.getId());
            clientVehicleDetail.setModelYear(vehicleDetail.getModelYear());
            clientVehicleDetail.setBrandName(vehicleDetail.getBrandName());
            clientVehicleDetail.setModelName(vehicleDetail.getModelName());
            clientVehicleDetail.setTrimType(vehicleDetail.getTrimType());
            clientVehicleDetail.setBodyType(vehicleDetail.getBodyType());
            clientVehicleDetail.setPrice(vehicleDetail.getPrice());
            clientVehicleDetail.setMiles(vehicleDetail.getMiles());
            clientVehicleDetail.setLocation(vehicleDetail.getLocation());
            clientVehicleDetail.setSeller(vehicleDetail.getSeller());
            clientVehicleDetail.setSellerPhone(vehicleDetail.getSellerPhone());

            //calculate estimated monthly price
            //(price/(5*12)+price*interest rate/(100*12)
            double monthlyPrice = vehicleDetail.getPrice()/(5*12)+vehicleDetail.getPrice()* vehicleDetail.getInterestRate()/(100*12);
            //$312.56 / monthly est.
            clientVehicleDetail.setEstimatedMonthly("$"+monthlyPrice+" / monthly est.");

            //Calculate Amount below or above market price

            //calculate current market price
            //Market Price (New Vehicle) - (current year - model year)*market price*0.5/25 - current miles*market price*75/500000
            VehicleMarketPrice dbVehicleMarketPrice = vehicleMarketPriceService.getVehicleMarketPriceByBrandModel(vehicleDetail.getBrandName(), vehicleDetail.getModelName());
            double currentVehicleMarketPrice = dbVehicleMarketPrice.getPrice()
                    - (LocalDate.now().getYear()-Integer.parseInt(vehicleDetail.getModelYear())
                    *dbVehicleMarketPrice.getPrice()*0.5/25)
                    - (vehicleDetail.getMiles()*dbVehicleMarketPrice.getPrice()*75/500000);

            if(currentVehicleMarketPrice < 0){
                currentVehicleMarketPrice = 0;
            }

            double marketPriceComparison = currentVehicleMarketPrice - vehicleDetail.getPrice();

            if(marketPriceComparison>0){
                clientVehicleDetail.setAmountBelowMarket("$"+marketPriceComparison+" above market price.");
            } else {
                clientVehicleDetail.setAmountBelowMarket("-$"+marketPriceComparison+" below market price.");
            }

            clientVehicleDetailsList.add(clientVehicleDetail);
        }
        return clientVehicleDetailsList;
    }
}