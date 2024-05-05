package com.wesleypi.cars.domain.dto.bhut;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Year;
import java.util.UUID;
    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    public class BhutGetCarResponse {
        private UUID id;
        @JsonProperty("nome")
        private String name;
        @JsonProperty("marca")
        private String brand;
        @JsonProperty("preco")
        private BigDecimal price;
        @JsonProperty("anoFabricacao")
        private Year manufactureYear;
        @JsonProperty("ativo")
        private Boolean active;
        @JsonProperty("criadoEm")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime creationAt;
        @JsonProperty("atualizadoEm")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime updatedAt;
}
