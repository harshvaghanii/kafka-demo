package com.kafka_project.user_service.service;

import com.kafka_project.user_service.dto.UserDto;
import com.kafka_project.user_service.entity.UserEntity;
import com.kafka_project.user_service.event.UserCreatedEvent;
import com.kafka_project.user_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    @Value("${spring.kafka.topic.user-created_topic}")
    private String KAFKA_USER_CREATED_TOPIC;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final KafkaTemplate<Long, UserCreatedEvent> kafkaTemplate;

    public UserDto saveUser(UserDto userDto) {
        UserEntity toSaveUser = modelMapper.map(userDto, UserEntity.class);
        UserEntity savedUser = userRepository.save(toSaveUser);
        UserCreatedEvent userCreatedEvent = modelMapper.map(savedUser, UserCreatedEvent.class);

        kafkaTemplate.send(KAFKA_USER_CREATED_TOPIC, userCreatedEvent);

        return modelMapper.map(savedUser, UserDto.class);
    }

}
