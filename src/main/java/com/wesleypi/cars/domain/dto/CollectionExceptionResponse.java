package com.wesleypi.cars.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class CollectionExceptionResponse {
    List<ExceptionResponse> error;
}
