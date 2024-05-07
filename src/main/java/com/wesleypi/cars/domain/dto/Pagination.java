package com.wesleypi.cars.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class Pagination {
    @JsonProperty("pagina")
    private Integer page;
    @JsonProperty("tamanhoPagina")
    private Integer pageSize;
    private Long total;
}
