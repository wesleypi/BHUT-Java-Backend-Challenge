package com.wesleypi.cars.service;

import com.wesleypi.cars.model.CarIntegrationModel;
import com.wesleypi.cars.model.external.CarsResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class ExternalService {

    @Value("${external-api.car-uri}")
    private String carUri;

    @Autowired
    AuthService authService;

    private final WebClient webClient;

    public ExternalService(WebClient webClientExternal){
        this.webClient = webClientExternal;
    }

    public CarIntegrationModel postCar(CarIntegrationModel carIntegrationModel){
        return webClient.post()
                .uri(carUri)
                .headers(httpHeaders -> httpHeaders.setBearerAuth(authService.getToken()))
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(carIntegrationModel), CarIntegrationModel.class)
                .retrieve()
                .bodyToMono(CarIntegrationModel.class).block();
    }

    public CarsResponse getCars(){
        return webClient.get()
                .uri(carUri)
                .headers(httpHeaders -> httpHeaders.setBearerAuth(authService.getToken()))
                .retrieve()
                .bodyToMono(CarsResponse.class).block();
    }
}