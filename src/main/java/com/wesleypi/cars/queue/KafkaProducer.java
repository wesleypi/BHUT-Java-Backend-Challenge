package com.wesleypi.cars.queue;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaProducer {

    @Value("${topic.create-car}")
    private String TOPIC;

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendFlightEvent(String message){
        kafkaTemplate.send(TOPIC, message);
        log.info("Producer produced the message {}", message);
    }
}