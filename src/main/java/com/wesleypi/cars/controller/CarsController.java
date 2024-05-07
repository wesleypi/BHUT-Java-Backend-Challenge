package com.wesleypi.cars.controller;

import com.wesleypi.cars.domain.dto.CreateCarRequest;
import com.wesleypi.cars.domain.dto.LogResponse;
import com.wesleypi.cars.domain.dto.bhut.BhutGetCarWithPaginationResponse;
import com.wesleypi.cars.service.queue.KafkaProducer;
import com.wesleypi.cars.service.BhutRequestService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Tag(name = "Cars API")
public class CarsController {

    private final BhutRequestService requestService;

    private final KafkaProducer kafkaProducer;

    public CarsController(BhutRequestService requestService, KafkaProducer kafkaProducer) {
        this.requestService = requestService;
        this.kafkaProducer = kafkaProducer;
    }
    @Operation(summary = "Lista carros da API Bhut")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "OK",
                            content = { @Content(mediaType = "application/json", schema = @Schema(implementation = BhutGetCarWithPaginationResponse.class)) }),
                    @ApiResponse(responseCode = "400", description = "Query params inválidos",
                            content = @Content)
            })
    @GetMapping("/car")
    @ResponseStatus(HttpStatus.OK)
    public BhutGetCarWithPaginationResponse getCars(@RequestParam(defaultValue = "1") int page,
                                                    @RequestParam(defaultValue = "5") int size){
        return requestService.getCars(page, size);
    }

    @Operation(summary = "Cria um carro na API Bhut e gera um log")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "202", description = "aceito",
                            content = { @Content(mediaType = "application/json", schema = @Schema(implementation = CreateCarRequest.class)) }),
                    @ApiResponse(responseCode = "400", description = "request body inválido",
                            content = @Content)
            })
    @PostMapping("/car")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public CreateCarRequest postCar(@Valid @RequestBody CreateCarRequest carRequest){
        carRequest.setId(requestService.postCar(carRequest).getId().toString());
        kafkaProducer.event(carRequest);
        return carRequest;
    }
}
