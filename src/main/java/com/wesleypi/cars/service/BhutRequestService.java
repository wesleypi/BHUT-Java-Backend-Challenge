package com.wesleypi.cars.service;

import com.wesleypi.cars.domain.dto.CreateCarRequest;
import com.wesleypi.cars.domain.dto.bhut.BhutCreateCar;
import com.wesleypi.cars.domain.dto.bhut.BhutGetCarWithPaginationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import static com.wesleypi.cars.domain.helper.HelperConverter.toBhutCarRequest;

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

    public BhutCreateCar postCar(CreateCarRequest carRequest){
        return webClient.post()
                .uri(carUri)
                .headers(httpHeaders -> httpHeaders.setBearerAuth(authService.getToken()))
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(toBhutCarRequest(carRequest)), BhutCreateCar.class)
                .retrieve()
                .bodyToMono(BhutCreateCar.class).block();
    }

    public BhutGetCarWithPaginationResponse getCars(int page, int size){
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(carUri)
                        .queryParam("pagina", page)
                        .queryParam("tamanhoPagina", size).build())
                .headers(httpHeaders -> httpHeaders.setBearerAuth(authService.getToken()))
                .retrieve()
                .bodyToMono(BhutGetCarWithPaginationResponse.class)
                .block();
    }
}