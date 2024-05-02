package com.wesleypi.cars.model.external;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class TokenRefreshResponse {
    @JsonProperty("tokenRenovado")
    private String renewedToken;
}
