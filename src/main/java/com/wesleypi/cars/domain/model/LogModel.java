package com.wesleypi.cars.domain.model;

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
    private String carId;
    private LocalDateTime creationDateHour;
    private LocalDateTime processingDateHour;
}
