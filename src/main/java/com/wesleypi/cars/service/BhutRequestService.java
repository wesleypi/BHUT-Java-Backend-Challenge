package com.wesleypi.cars.service;

import com.wesleypi.cars.domain.model.bhut.BhutCreateCarRequest;
import com.wesleypi.cars.domain.model.bhut.BhutGetCarCollectionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class BhutRequestService {

    @Value("${external-api.car-uri}")
    private String carUri;

    @Autowired
    BhutAuthService authService;

    private final WebClient webClient;

    public BhutRequestService(WebClient webClientExternal){
        this.webClient = webClientExternal;
    }

    public BhutCreateCarRequest postCar(BhutCreateCarRequest bhutCreateCarRequest){
        return webClient.post()
                .uri(carUri)
                .headers(httpHeaders ->
                        httpHeaders.setBearerAuth(authService.getToken()))
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(bhutCreateCarRequest),
                        BhutCreateCarRequest.class)
                .retrieve()
                .bodyToMono(BhutCreateCarRequest.class).block();
    }

    public BhutGetCarCollectionResponse getCars(){
        return webClient.get()
                .uri(carUri)
                .headers(httpHeaders ->
                        httpHeaders.setBearerAuth(authService.getToken()))
                .retrieve()
                .bodyToMono(BhutGetCarCollectionResponse.class).block();
    }
}