package com.mert.config.rabbit;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    private String exchangeDirectAuth = "exchange-direct-deneme-auth";
    private String keyAuth = "key-deneme-auth";
    private String queueAuthCreateUser = "queue-auth-create-deneme-user";


    @Bean
    DirectExchange topicExchange(){
        return new DirectExchange(exchangeDirectAuth);
    }
    @Bean
    Queue userQueue(){
        return new Queue(queueAuthCreateUser);
    }

    @Bean
    public Binding bindingCreateAuthUser(final Queue userQueue,final DirectExchange directExchange){
        return BindingBuilder.bind(userQueue).to(directExchange).with(keyAuth);
    }


}
