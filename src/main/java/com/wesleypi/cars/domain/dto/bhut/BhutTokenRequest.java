package com.wesleypi.cars.domain.dto.bhut;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BhutTokenRequest {
    @JsonProperty("login")
    private String name;
    @JsonProperty("senha")
    private String pass;
}
