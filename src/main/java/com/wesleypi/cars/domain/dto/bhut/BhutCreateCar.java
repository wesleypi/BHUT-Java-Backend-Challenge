package com.wesleypi.cars.domain.dto.bhut;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class BhutCreateCar {
    private UUID id;
    @JsonProperty("nome")
    private String name;
    @JsonProperty("marca")
    private String brand;
    @JsonProperty("preco")
    private BigDecimal price;
    @JsonProperty("anoFabricacao")
    private Integer manufactureYear;
}
