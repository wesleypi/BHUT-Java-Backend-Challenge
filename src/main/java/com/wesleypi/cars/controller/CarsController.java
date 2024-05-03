package com.wesleypi.cars.controller;

import com.wesleypi.cars.domain.model.bhut.BhutCreateCarRequest;
import com.wesleypi.cars.domain.model.bhut.BhutGetCarCollectionResponse;
import com.wesleypi.cars.queue.KafkaProducer;
import com.wesleypi.cars.service.BhutRequestService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class CarsController {

    private final BhutRequestService requestService;

    private final KafkaProducer kafkaProducer;

    public CarsController(BhutRequestService requestService, KafkaProducer kafkaProducer) {
        this.requestService = requestService;
        this.kafkaProducer = kafkaProducer;
    }

    @GetMapping("/car")
    @ResponseStatus(HttpStatus.OK)
    public BhutGetCarCollectionResponse getCars(){
        return requestService.getCars();
    }

    @PostMapping("/car")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public BhutCreateCarRequest postCar(@RequestBody BhutCreateCarRequest bhutCreateCarRequest){
        bhutCreateCarRequest.setId(
                requestService.postCar(bhutCreateCarRequest).getId());
        kafkaProducer.event(bhutCreateCarRequest);
        return bhutCreateCarRequest;
    }

}
