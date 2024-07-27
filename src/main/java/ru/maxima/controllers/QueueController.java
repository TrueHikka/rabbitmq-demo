package ru.maxima.controllers;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class QueueController {

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public QueueController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    //* Для fanout & direct
    @PostMapping("/ack")
    public ResponseEntity<String> ack(@RequestBody Map<String, String> map) {
        System.out.println("In controller: " + map.values());

//        for (int i = 0; i < 10; i++) {
//            rabbitTemplate.setExchange("myDirectExchange");
//            rabbitTemplate.convertAndSend(map.get("key"), map.get("msg"));
//        }

        rabbitTemplate.setExchange("myDirectExchange");
        rabbitTemplate.convertAndSend(map.get("key"), map.get("msg"));

        return ResponseEntity.ok("Successful sending " + map.get("msg"));
    }

//    @PostMapping("/ack")
//    public ResponseEntity<String> ack(@RequestBody String message) {
//
//        System.out.println("In controller: " + message);
//
//        rabbitTemplate.setExchange("myDirectExchange");
//        rabbitTemplate.setRoutingKey("admin");
//        rabbitTemplate.convertAndSend(message);
//
//        return ResponseEntity.ok("Successful sending " + message);
        //!
//        for (int i = 0; i < 10; i++) {
//            rabbitTemplate.setExchange("myDirectExchange");
//            rabbitTemplate.convertAndSend(message);
//        }


        //!
//        rabbitTemplate.convertAndSend("myFirstQueue", message);
//
//        rabbitTemplate.convertAndSend("myFirstQueue", message);

//    }


    //* Для topic
//    @PostMapping("/ack")
//    public ResponseEntity<String> ack(@RequestBody Map<String, String> map) {
//        System.out.println("In controller: " + map.values());
//
//        rabbitTemplate.setExchange("myTopicExchange");
//        rabbitTemplate.convertAndSend(map.get("key"), map.get("msg"));
//
//        return ResponseEntity.ok("Successful sending " + map.get("msg"));
//    }
}
