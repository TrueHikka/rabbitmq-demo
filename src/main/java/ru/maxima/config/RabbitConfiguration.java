package ru.maxima.config;

import lombok.Data;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
@EnableRabbit
public class RabbitConfiguration {

    @Bean
    public ConnectionFactory connectionFactory() {
        return new CachingConnectionFactory("localhost");
    }

    @Bean
    public AmqpAdmin amqpAdmin() {
        return new RabbitAdmin(connectionFactory());
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        return new RabbitTemplate(connectionFactory());
    }

    @Bean
    public Queue myFirstQueue() {
//        return QueueBuilder.durable("myFirstQueue").build();
       return new Queue("myFirstQueue", false);
    }

    @Bean
    public Queue mySecondQueue() {
//        return QueueBuilder.durable("mySecondQueue").build();
        return new Queue("mySecondQueue", false);
    }

    @Bean
    public Queue myThirdQueue() {
//        return QueueBuilder.durable("mySecondQueue").build();
        return new Queue("myThirdQueue", false);
    }

//    @Bean
//    public Queue myThirdQueue() {
//        return new Queue("myThirdQueue");
//    }

    //* 1 - FanoutExchange
//    @Bean
//    public FanoutExchange myExchange() {
//        return new FanoutExchange("myExchange");
//    }
//
//    @Bean
//    public Binding binding1() {
//        return BindingBuilder.bind(myFirstQueue()).to(myExchange());
//    }
//
//    @Bean
//    public Binding binding2() {
//        return BindingBuilder.bind(mySecondQueue()).to(myExchange());
//    }


    //* 2 - DirectExchange
    @Bean
    public DirectExchange myDirectExchange() {
//        return  ExchangeBuilder.directExchange("myDirectExchange").durable(true).build();

        return new DirectExchange("myDirectExchange");
    }

    @Bean
    public Binding binding1() {
        return BindingBuilder.bind(myFirstQueue()).to(myDirectExchange()).with("user");
    }

    @Bean
    public Binding binding2() {
        return BindingBuilder.bind(mySecondQueue()).to(myDirectExchange()).with("admin");
    }

    @Bean
    public Binding binding3() {
        return BindingBuilder.bind(myThirdQueue()).to(myDirectExchange()).with("hr");
    }

    //* 3 - TopicExchange
//    @Bean
//    public TopicExchange myTopicExchange() {
////        return ExchangeBuilder.topicExchange("myTopicExchange").durable(true).build();
//        return new TopicExchange("myTopicExchange");
//    }
//    @Bean
//    public Binding binding1() {
//        return BindingBuilder.bind(myFirstQueue()).to(myTopicExchange()).with("first.*"); //все сообщения начинаются с "first"
//    }
//
//    @Bean
//    public Binding binding2() {
//        return BindingBuilder.bind(mySecondQueue()).to(myTopicExchange()).with("#.second.#"); //все сообщения заканчиваются на "second"
//    }
//
//    @Bean
//    public Binding binding3() {
//        return BindingBuilder.bind(myThirdQueue()).to(myTopicExchange()).with("*.third.*"); //
//    }



    //! Заместо этого используем RabbitListener.java
//    @Bean
//    public SimpleMessageListenerContainer simpleMessageListenerContainer() {
//        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
//        simpleMessageListenerContainer.setConnectionFactory(connectionFactory());
//        simpleMessageListenerContainer.setQueueNames("myFirstQueue");
//
//        simpleMessageListenerContainer.setMessageListener(msg -> {
//            System.out.println("Received message from MyFirstQueue : " + msg.toString());
//            System.out.println("Message headers: " + msg.getMessageProperties().getHeaders());
//            System.out.println("Message body: " + new String(msg.getBody()));
//        });
//
//        return simpleMessageListenerContainer;
//
//    }
}
