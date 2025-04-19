package com.kafka_project.notification_service.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserKafkaConsumer {

    @KafkaListener(topics = "${spring.kafka.topic.user-random_topic}")
    public void handleUserRandomTopic0(String message) {
        log.info("Message Received in client 0: {}", message);
    }

    @KafkaListener(topics = "${spring.kafka.topic.user-random_topic}")
    public void handleUserRandomTopic1(String message) {
        log.info("Message Received in client 1: {}", message);
    }

    @KafkaListener(topics = "${spring.kafka.topic.user-random_topic}")
    public void handleUserRandomTopic2(String message) {
        log.info("Message Received in client 2: {}", message);
    }

}
