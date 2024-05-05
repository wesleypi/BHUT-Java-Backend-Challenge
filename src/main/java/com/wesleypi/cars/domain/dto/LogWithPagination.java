package com.wesleypi.cars.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class LogWithPagination {
    @JsonProperty("paginacao")
    private Pagination paginationModel;
    @JsonProperty("itens")
    private List<LogResponse> logs;
}
