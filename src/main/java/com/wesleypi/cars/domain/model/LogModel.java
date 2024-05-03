package com.wesleypi.cars.domain.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@Document("logs")
public class LogModel {
    @Id
    private String id;
    @JsonProperty("car_id")
    private String carId;
    @JsonProperty("data_hora_criacao")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime creationDateHour;
    @JsonProperty("data_hora_processamento")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime processingDateHour;
}
