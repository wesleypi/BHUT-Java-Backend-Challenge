package com.wesleypi.cars.model.external;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class CarsResponse {
    @JsonProperty("paginacao")
    private PaginationResponse Pagination;
    @JsonProperty("itens")
    private List<CarResponse> cars;
}