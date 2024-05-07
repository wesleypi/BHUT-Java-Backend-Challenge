package com.wesleypi.cars.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@NoArgsConstructor
@Data
public class CarQueue {
    @JsonProperty("car_id")
    private String carId;
    @JsonProperty("data_hora_criacao")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime creationDateHour;
    private String webhookURL;

    public CarQueue(CreateCarRequest carRequest){
        this.carId = carRequest.getId();
        this.creationDateHour = LocalDateTime.now();
        this.webhookURL = carRequest.getWebhookURL();
    }
}
