package com.wesleypi.cars.service;

import com.wesleypi.cars.model.CarIntegrationModel;
import com.wesleypi.cars.model.CredentialRequest;
import com.wesleypi.cars.model.external.CarsResponse;
import com.wesleypi.cars.model.external.TokenResponse;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class ExternalService {

    @Value("${api.external.user}")
    private String name;

    @Value("${api.external.pass}")
    private String pass;

    @Value("api.external.default.url")
    private String url;

    @Value("api.external.car")
    private String carUri;

    @Value("api.external.token")
    private String tokenUri;

    @Value("api.external.refresh.token")
    private String refreshTokenUri;

    private String token = "";

    private WebClient webClient;

    @PostConstruct
    private void postConstruct() {
        this.webClient = WebClient.create(url);
    }

    public TokenResponse getToken(){
        return webClient.post()
                .uri(tokenUri)
                .contentType(MediaType.APPLICATION_JSON)
                .body(CredentialRequest.toRequestBody(name,pass), CredentialRequest.class)
                .retrieve()
                .bodyToMono(TokenResponse.class).block();
    }

    public TokenResponse getRefreshToken(){
        return webClient.post()
                .uri(refreshTokenUri)
                .contentType(MediaType.APPLICATION_JSON)
                .body(token, String.class)
                .retrieve()
                .bodyToMono(TokenResponse.class).block();
    }

    public CarIntegrationModel postCar(CarIntegrationModel carIntegrationModel){
        if (token.isEmpty()) {
            token = getToken().getAccessToken();
        }
        return webClient.post()
                .uri(carUri)
                .headers(h-> h.setBearerAuth(token))
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(carIntegrationModel), CarIntegrationModel.class)
                .retrieve()
                .bodyToMono(CarIntegrationModel.class).block();
    }

    //TODO: adicionar timer para o refresh token

    public CarsResponse getCars(){
        if (token.isEmpty()) {
            token = getToken().getAccessToken();
        }

        return webClient.get()
                .uri(carUri)
                .headers(h -> h.setBearerAuth(token))
                .retrieve()
                .bodyToMono(CarsResponse.class).block();
    }
}