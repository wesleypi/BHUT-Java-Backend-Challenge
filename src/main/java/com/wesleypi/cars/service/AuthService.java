package com.wesleypi.cars.service;

import com.wesleypi.cars.model.CredentialRequest;
import com.wesleypi.cars.model.external.TokenRefreshResponse;
import com.wesleypi.cars.model.external.TokenResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Optional;


@Service
@Slf4j
public class AuthService {

    @Value("${external-api.user}")
    private String name;

    @Value("${external-api.pass}")
    private String pass;

    @Value("${external-api.token-uri}")
    private String tokenUri;

    @Value("${external-api.refresh-token-uri}")
    private String refreshTokenUri;

    private LocalDateTime expirationTime;

    private TokenResponse tokenResponse;

    private final WebClient webClient;

    public AuthService(WebClient webClientExternal){
        this.webClient = webClientExternal;
        this.tokenResponse = new TokenResponse();
    }

    public String getToken(){

        if(tokenResponse.getAccessToken() == null){
            tokenResponse = createToken();

            expirationTime = LocalDateTime.now()
                    .plusSeconds(10);

            return tokenResponse.getAccessToken();
        }

        if(isExpired()){
            tokenResponse = refreshToken();

            expirationTime = LocalDateTime.now()
                    .plusSeconds(expirationTime.getSecond());
        }

        return tokenResponse.getAccessToken();
    }

    public TokenResponse createToken(){
        return webClient.post()
                .uri(tokenUri)
                .contentType(MediaType.APPLICATION_JSON)
                .body(CredentialRequest.toRequestBody(name,pass), CredentialRequest.class)
                .retrieve()
                .bodyToMono(TokenResponse.class).block();
    }

    public TokenResponse refreshToken(){
        return webClient.post()
                .uri(refreshTokenUri)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(new TokenRefreshResponse(tokenResponse.getRefreshToken())), TokenRefreshResponse.class)
                .retrieve()
                .bodyToMono(TokenResponse.class).block();
    }

    public boolean isExpired(){
        return LocalDateTime.now().isAfter(expirationTime);
    }
}