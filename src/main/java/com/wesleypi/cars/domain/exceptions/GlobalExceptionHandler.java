package com.wesleypi.cars.domain.exceptions;

import com.wesleypi.cars.domain.dto.CollectionExceptionResponse;
import com.wesleypi.cars.domain.dto.ExceptionResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(WebClientResponseException.class)
    public ResponseEntity<ExceptionResponse> notFoundException(WebClientResponseException e){
        return ResponseEntity.status(e.getStatusCode())
                .body(new ExceptionResponse(e.getStatusCode().toString(), e.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CollectionExceptionResponse> notValidArgumentException(MethodArgumentNotValidException e){
        return ResponseEntity.status(e.getStatusCode())
                .body(new CollectionExceptionResponse(e.getAllErrors().stream()
                                .map(error -> new ExceptionResponse(e.getStatusCode().toString()
                                        , error.getDefaultMessage())).toList()));
    }
}