package com.wesleypi.cars.domain.dto.bhut;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class BhutTokenRefreshRequest {
    @JsonProperty("tokenRenovado")
    private String renewedToken;
}
