package com.wesleypi.cars.domain.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class WebClienteResponse {
    private String httpStatus;
    private String message;
}
