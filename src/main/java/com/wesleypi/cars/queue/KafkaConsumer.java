package com.wesleypi.cars.queue;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class KafkaConsumer {

    @KafkaListener(topics = "topic-create-car", groupId = "api-car")
    public void flightEventConsumer(String message) {
        log.info("Consumer consume Kafka message -> {}", message);

        // write your handlers and post-processing logic, based on your use case
    }
}