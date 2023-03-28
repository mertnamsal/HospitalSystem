package com.mert.config.rabbit;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    private String exchangeDirectAuth = "exchange-direct-deneme-auth";
    private String keyAuth = "key-deneme-auth";
    private String queueAuthCreateUser = "queue-auth-create-deneme-user";
    private String queueAuthCreateAppointment = "queue-auth-create-deneme-appointment";

    @Bean
    TopicExchange topicExchange(){
        return new TopicExchange("topic-exchange");
    }
    @Bean
    Queue userQueue(){
        return new Queue(queueAuthCreateUser);
    }
    @Bean
    Queue appointmentQueue(){
        return new Queue(queueAuthCreateAppointment);
    }
    @Bean
    public Binding bindingCreateAuthUser(final Queue userQueue,final TopicExchange topicExchange){
        return BindingBuilder.bind(userQueue).to(topicExchange).with("queue.deneme");
    }
    @Bean
    public Binding bindingCreateAuthAppointment(final Queue appointmentQueue,final TopicExchange topicExchange){
        return BindingBuilder.bind(appointmentQueue).to(topicExchange).with("queue.deneme2");
    }

}
