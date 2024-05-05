package com.wesleypi.cars.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
@Setter
public class CreateCarRequest {
    private String id;
    @NotBlank(message = "Necessário inserir um nome")
    @JsonProperty("nome")
    private String name;
    @NotBlank(message = "Necessário inserir uma marca")
    @JsonProperty("marca")
    private String brand;
    @NotNull(message = "Necessário inserir um valor para preco")
    @DecimalMin(value = "10000.00", message = "Necessário inserir um preco acima de 10000.00")
    @JsonProperty("preco")
    private BigDecimal price;
    @NotNull(message = "Necessário inserir um valor para anoFabricacao")
    @Range(min = 1950, max = 2024, message = "anoFabricacao precisa estar entre 1950 e 2024")
    @JsonProperty("anoFabricacao")
    private Integer manufactureYear;
    private String webhookURL;
}
