package com.wesleypi.cars.domain.model.bhut;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class BhutCreateCarRequest {
    private UUID id;
    @JsonProperty("nome")
    private String name;
    @JsonProperty("marca")
    private String brand;
    @JsonProperty("preco")
    private BigDecimal price;
    @JsonProperty("anoFabricacao")
    private Integer manufactureYear;
    private String webhookURL;
}
