package ru.maxima.listeners;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@EnableRabbit
@Component
public class RabbitMqListener {

    @RabbitListener(queues = "myFirstQueue")
    public void processMsg(String message) {
        System.out.println("Message body: " + message);
    }

}
