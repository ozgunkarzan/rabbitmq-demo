package com.ozgun.springbootrabbitmqdemo.publisher;

import com.ozgun.springbootrabbitmqdemo.dto.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQJsonProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQProducer.class);

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.json-routing-key}")
    private String jsonRoutingKey;

    private final RabbitTemplate rabbitTemplate;

    public RabbitMQJsonProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendJsonMessage(UserDTO userDTO) {
        LOGGER.info(String.format("Sending user with  %s username to RabbitMQ", userDTO.getUsername()));
        rabbitTemplate.convertAndSend(exchange, jsonRoutingKey, userDTO);
    }

}
