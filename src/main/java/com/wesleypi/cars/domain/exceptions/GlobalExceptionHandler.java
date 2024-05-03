package com.wesleypi.cars.domain.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(WebClientResponseException.class)
    public ResponseEntity<WebClienteResponse> NotFoundException(WebClientResponseException e){

        return ResponseEntity.status(e.getStatusCode())
                .body(new WebClienteResponse(e.getStatusCode().toString(), e.getMessage()));
    }
}