package com.ozgun.springbootrabbitmqdemo.consumer;

import com.ozgun.springbootrabbitmqdemo.dto.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQJsonConsumer {

    private static final Logger LOGGER= LoggerFactory.getLogger(RabbitMQJsonConsumer.class);

    @RabbitListener(queues = {"${rabbitmq.json-queue.name}"})
    public void consumeJsonMessage(UserDTO userDTO) {
        LOGGER.info(String.format("Received user message : %s", userDTO.getUsername()));
    }
}
