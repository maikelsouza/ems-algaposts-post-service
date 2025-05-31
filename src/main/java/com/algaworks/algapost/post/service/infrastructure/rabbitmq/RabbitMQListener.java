package com.algaworks.algapost.post.service.infrastructure.rabbitmq;

import com.algaworks.algapost.post.service.api.model.PostProcessingResultMessage;
import com.algaworks.algapost.post.service.domain.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RabbitMQListener {

    private final PostService service;

    @RabbitListener(queues = RabbitMQConfig.QUEUE_POST_PROCESSING_RESULT)
    public void handle(@Payload PostProcessingResultMessage postProcessingResultMessage) {
        service.updateValuePost(postProcessingResultMessage);
    }
}
