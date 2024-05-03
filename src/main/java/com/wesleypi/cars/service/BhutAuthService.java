package com.wesleypi.cars.service;

import com.wesleypi.cars.domain.model.bhut.BhutTokenRefreshRequest;
import com.wesleypi.cars.domain.model.bhut.BhutTokenRequest;
import com.wesleypi.cars.domain.model.bhut.BhutTokenResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import java.time.LocalDateTime;


@Service
public class BhutAuthService {

    @Value("${external-api.user}")
    private String name;

    @Value("${external-api.pass}")
    private String pass;

    @Value("${external-api.token-uri}")
    private String tokenUri;

    @Value("${external-api.refresh-token-uri}")
    private String refreshTokenUri;

    private LocalDateTime expirationTime;

    private BhutTokenResponse tokenResponse;

    private final WebClient webClient;

    public BhutAuthService(WebClient webClientExternal){
        this.webClient = webClientExternal;
    }

    public String getToken(){

        if(tokenResponse == null){
            tokenResponse = createToken();

            expirationTime = refreshExpiration(tokenResponse.getExpiresIn());

            return tokenResponse.getAccessToken();
        }

        if(isExpired()){
            tokenResponse = refreshToken();

            expirationTime = refreshExpiration(tokenResponse.getExpiresIn());
        }

        return tokenResponse.getAccessToken();
    }

    private LocalDateTime refreshExpiration(Long secondsToExpire){
        return LocalDateTime.now().plusSeconds(secondsToExpire);
    }

    private boolean isExpired(){
        return LocalDateTime.now().isAfter(expirationTime);
    }

    public BhutTokenResponse createToken(){
        return webClient.post()
                .uri(tokenUri)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(new BhutTokenRequest(name, pass))
                        , BhutTokenRequest.class)
                .retrieve()
                .bodyToMono(BhutTokenResponse.class).block();
    }

    public BhutTokenResponse refreshToken(){
        return webClient.post()
                .uri(refreshTokenUri)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(new BhutTokenRefreshRequest(tokenResponse.getRefreshToken()))
                        , BhutTokenRefreshRequest.class)
                .retrieve()
                .bodyToMono(BhutTokenResponse.class).block();
    }
}