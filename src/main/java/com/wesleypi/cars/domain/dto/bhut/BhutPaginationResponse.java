package com.wesleypi.cars.domain.dto.bhut;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BhutPaginationResponse {
    @JsonProperty("pagina")
    private Integer page;
    @JsonProperty("tamanhoPagina")
    private Integer pageSize;
    private Long total;
}