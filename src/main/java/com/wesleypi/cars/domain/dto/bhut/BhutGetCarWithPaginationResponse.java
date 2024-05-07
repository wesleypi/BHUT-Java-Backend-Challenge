package com.wesleypi.cars.domain.dto.bhut;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class BhutGetCarWithPaginationResponse {
    @JsonProperty("paginacao")
    private BhutPaginationResponse Pagination;
    @JsonProperty("itens")
    private List<BhutGetCarResponse> cars;
}