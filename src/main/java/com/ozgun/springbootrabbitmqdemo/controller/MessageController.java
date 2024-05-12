package com.ozgun.springbootrabbitmqdemo.controller;

import com.ozgun.springbootrabbitmqdemo.dto.UserDTO;
import com.ozgun.springbootrabbitmqdemo.publisher.RabbitMQJsonProducer;
import com.ozgun.springbootrabbitmqdemo.publisher.RabbitMQProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class MessageController {

    private final RabbitMQProducer rabbitMQProducer;
    private final RabbitMQJsonProducer rabbitMQJsonProducer;

    public MessageController(RabbitMQProducer rabbitMQProducer, RabbitMQJsonProducer rabbitMQJsonProducer) {
        this.rabbitMQProducer = rabbitMQProducer;
        this.rabbitMQJsonProducer = rabbitMQJsonProducer;
    }

    @GetMapping("/publish")
    public ResponseEntity<String> sendMessage(@RequestParam("message") String message) {
        rabbitMQProducer.sendMessage(message);
        return ResponseEntity.ok( String.format("%s Message Send", message));
    }

    @PostMapping("/jsonpublish")
    public ResponseEntity<String> sendJsonMessage(@RequestBody UserDTO userDTO) {
        rabbitMQJsonProducer.sendJsonMessage(userDTO);
        return ResponseEntity.ok( String.format("User with %s username is Send to queue", userDTO.getUsername()));
    }
}
