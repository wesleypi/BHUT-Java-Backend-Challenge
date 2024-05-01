package com.wesleypi.cars.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;


import java.math.BigDecimal;
import java.time.Year;
import java.util.UUID;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarIntegrationModel {
    private UUID id;
    @JsonProperty("nome")
    private String name;
    @JsonProperty("marca")
    private String brand;
    @JsonProperty("preco")
    private BigDecimal price;
    @JsonProperty("anoFabricacao")
    private Year manufactureYear;
}
