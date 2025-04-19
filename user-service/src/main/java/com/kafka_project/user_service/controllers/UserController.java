package com.kafka_project.user_service.controllers;

import com.kafka_project.user_service.dto.MessageDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/users")
@Slf4j
@RequiredArgsConstructor
public class UserController {

    @Value("${spring.kafka.topic.user-random_topic}")
    private String KAFKA_RANDOM_USER_TOPIC;

    private final KafkaTemplate<String, String> kafkaTemplate;

    @PostMapping()
    public ResponseEntity<String> sendMessage(@RequestBody MessageDto messageDto) {
        kafkaTemplate.send(KAFKA_RANDOM_USER_TOPIC, messageDto.getMessage());
        return ResponseEntity.ok("Message queued!");
    }

}
