package com.wesleypi.cars.controller;

import com.wesleypi.cars.domain.dto.CreateCarRequest;
import com.wesleypi.cars.domain.dto.bhut.BhutGetCarWithPaginationResponse;
import com.wesleypi.cars.service.queue.KafkaProducer;
import com.wesleypi.cars.service.BhutRequestService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api")
public class CarsController {

    private final BhutRequestService requestService;

    private final KafkaProducer kafkaProducer;

    public CarsController(BhutRequestService requestService, KafkaProducer kafkaProducer) {
        this.requestService = requestService;
        this.kafkaProducer = kafkaProducer;
    }

    @GetMapping("/car")
    @ResponseStatus(HttpStatus.OK)
    public BhutGetCarWithPaginationResponse getCars(@RequestParam(defaultValue = "1") int page,
                                                    @RequestParam(defaultValue = "5") int size){
        return requestService.getCars(page, size);
    }

    @PostMapping("/car")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public CreateCarRequest postCar(@Valid @RequestBody CreateCarRequest carRequest){
        carRequest.setId(requestService.postCar(carRequest).getId().toString());
        kafkaProducer.event(carRequest);
        return carRequest;
    }
}
