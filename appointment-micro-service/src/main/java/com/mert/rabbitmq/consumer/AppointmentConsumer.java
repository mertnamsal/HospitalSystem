package com.mert.rabbitmq.consumer;

import com.mert.rabbitmq.model.CreateUser;
import com.mert.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppointmentConsumer {

    private final AppointmentService appointmentService;


}
