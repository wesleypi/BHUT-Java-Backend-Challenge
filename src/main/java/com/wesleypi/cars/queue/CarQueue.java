package com.wesleypi.cars.queue;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.wesleypi.cars.domain.model.bhut.BhutCreateCarRequest;
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

    public CarQueue(BhutCreateCarRequest bhutCreateCarRequest){
        this.carId = bhutCreateCarRequest.getId().toString();
        this.creationDateHour = LocalDateTime.now();
        this.webhookURL = bhutCreateCarRequest.getWebhookURL();
    }
}
