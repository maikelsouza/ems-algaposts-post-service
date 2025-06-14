package com.algaworks.algapost.post.service.infrastructure.rabbitmq;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class RebbitMQInitializer {

    private final RabbitAdmin rabbitAdmin;

    @PostConstruct
    public void init(){
        rabbitAdmin.initialize();
    }
}
