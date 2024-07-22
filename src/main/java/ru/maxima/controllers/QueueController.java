package ru.maxima.controllers;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class QueueController {

    private final AmqpTemplate amqpTemplate;

    @Autowired
    public QueueController(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    @PostMapping("/ack")
    public ResponseEntity<String> ack(@RequestBody String message) {

        System.out.println("Ack: " + message);

        amqpTemplate.convertAndSend("myFirstQueue", message);

        return ResponseEntity.ok("Successful sending " + message);
    }
}
