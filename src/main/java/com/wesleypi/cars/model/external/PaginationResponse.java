package com.wesleypi.cars.model.external;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PaginationResponse {
    @JsonProperty("pagina")
    private Integer page;
    @JsonProperty("tamanhoPagina")
    private Integer pageSize;
    private Integer total;
}
