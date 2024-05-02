package com.wesleypi.cars.model.external;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TokenResponse {
    private String accessToken;
    private String refreshToken;
    private String tokenType;
    private Long expiresIn;
}