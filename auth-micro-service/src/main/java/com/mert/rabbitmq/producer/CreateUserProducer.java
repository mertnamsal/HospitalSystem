package com.mert.rabbitmq.producer;

import com.mert.rabbitmq.model.CreateUser;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateUserProducer {
    private final RabbitTemplate rabbitTemplate;

    public void createSendMessage(CreateUser createUser){
        rabbitTemplate.convertAndSend("topic-exchange","queue.deneme",createUser);
    }
}
