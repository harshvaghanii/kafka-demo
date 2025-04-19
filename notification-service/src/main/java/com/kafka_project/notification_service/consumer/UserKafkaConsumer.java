package com.kafka_project.notification_service.consumer;

import com.kafka_project.user_service.event.UserCreatedEvent;
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

    @KafkaListener(topics = "${spring.kafka.topic.user-created_topic}")
    public void handleUserCreatedTopic(UserCreatedEvent userCreatedEvent) {
        log.info("User created with Email: {}", userCreatedEvent.getEmail());
    }

}
