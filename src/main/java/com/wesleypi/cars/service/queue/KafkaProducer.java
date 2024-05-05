package com.wesleypi.cars.service.queue;

import com.wesleypi.cars.domain.dto.CarQueue;
import com.wesleypi.cars.domain.dto.CreateCarRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaProducer {

    @Value("${topic.create-car}")
    private String topic;

    private final KafkaTemplate<String, CarQueue> kafkaTemplate;

    public void event(CreateCarRequest car){
        kafkaTemplate.send(topic, new CarQueue(car));
    }
}