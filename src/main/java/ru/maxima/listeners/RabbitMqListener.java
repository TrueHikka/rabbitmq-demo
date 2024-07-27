package ru.maxima.listeners;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

@EnableRabbit
@Component
public class RabbitMqListener {

    //* Для topic
    @RabbitListener(queues = "myFirstQueue")
    public void processMsg1(String message) {
        System.out.println("Received first message from MyFirstQueue: " + message);
    }

    @RabbitListener(queues = "myFirstQueue")
    public void processMsgExtra1(String message) {
        System.out.println("Received Extra message from MyFirstQueue: " + message);
    }

//    @RabbitListener(queues = "mySecondQueue")
//    public void processMsg2(String message) {
//        System.out.println("Received second message from mySecondQueue: " + message);
//    }

    @RabbitListener(queues = "myThirdQueue")
    public void processMsg3(String message) {
        System.out.println("Received third message from myThirdQueue: " + message);
    }

    //* Для fanout & direct
//    @RabbitListener(bindings = @QueueBinding(
//            value = @Queue(
//                    value = "myFirstQueue",
//                    durable = "false"
//            ),
//            exchange = @Exchange(
//                    value = "myDirectExchange"
//            ),
//            key = "admin"
//    ))
//    public void processMsg1(String message) {
//        System.out.println("Received 1 message from MyFirstQueue: " + message);
//    }
//
//    @RabbitListener(bindings = @QueueBinding(
//            value = @Queue(
//                    value = "mySecondQueue",
//                    durable = "false"
//            ),
//            exchange = @Exchange(
//                    value = "myDirectExchange"
//            ),
//            key = "HR"
//    ))
//    public void processMsg2(String message) {
//        System.out.println("Received 2 message from mySecondQueue: " + message);
//    }

}
