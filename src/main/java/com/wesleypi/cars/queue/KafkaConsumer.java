package com.wesleypi.cars.queue;

import com.wesleypi.cars.domain.model.LogModel;
import com.wesleypi.cars.service.LogsService;
import com.wesleypi.cars.service.WebhookService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class KafkaConsumer {

    @Autowired
    LogsService logsService;

    @Autowired
    WebhookService webhookService;

    @KafkaListener(topics = "topic-create-car", groupId = "api-car")
    public void event(ConsumerRecord<String, CarQueue> record) {
        logsService.create(LogModel.builder()
                .carId(record.value().getCarId())
                .creationDateHour(record.value().getCreationDateHour())
                .processingDateHour(LocalDateTime.now()).build());

        webhookService.postWebhook(record.value().getWebhookURL());
    }

}