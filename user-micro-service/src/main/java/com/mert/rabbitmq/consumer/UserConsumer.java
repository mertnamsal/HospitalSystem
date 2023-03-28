package com.mert.rabbitmq.consumer;

import com.mert.rabbitmq.model.CreateUser;
import com.mert.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserConsumer {

    private final UserProfileService userProfileService;

    @RabbitListener(queues = "queue-auth-create-deneme-user")
    public void createUserConsumerListener(CreateUser createUser){
        userProfileService.save(createUser);
    }
}
