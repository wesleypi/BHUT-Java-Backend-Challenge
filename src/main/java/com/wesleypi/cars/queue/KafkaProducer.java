package com.wesleypi.cars.queue;

import com.wesleypi.cars.domain.model.bhut.BhutCreateCarRequest;
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

    public void event(BhutCreateCarRequest car){
        kafkaTemplate.send(topic, new CarQueue(car));
    }
}