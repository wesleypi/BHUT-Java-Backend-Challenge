package com.wesleypi.cars.controller;

import com.wesleypi.cars.model.CarIntegrationModel;
import com.wesleypi.cars.model.external.CarsResponse;
import com.wesleypi.cars.service.ExternalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class CarsController {


    private final ExternalService externalService;

    public CarsController(ExternalService externalService) {
        this.externalService = externalService;
    }

    @GetMapping("/car")
    public CarsResponse getCars(){
        return externalService.getCars();
    }

    @PostMapping("/car")
    public CarIntegrationModel postCar(@RequestBody CarIntegrationModel carIntegrationModel){
        return externalService.postCar(carIntegrationModel);
    }

}
