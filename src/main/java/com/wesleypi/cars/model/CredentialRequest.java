package com.wesleypi.cars.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import reactor.core.publisher.Mono;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class CredentialRequest {
    @JsonProperty("login")
    private String name;
    @JsonProperty("senha")
    private String pass;

    public static Mono<CredentialRequest> toRequestBody(String name, String pass){
        return Mono.just(new CredentialRequest(name, pass));
    }
}
