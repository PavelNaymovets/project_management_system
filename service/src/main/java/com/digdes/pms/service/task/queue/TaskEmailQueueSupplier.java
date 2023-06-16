package com.digdes.pms.service.task.queue;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class TaskEmailQueueSupplier {
    @Value("${queue.exchanger}")
    private String exchanger;
    @Value("${queue.routingKey}")
    private String routingKey;

    private final RabbitTemplate rabbitTemplate;

    public TaskEmailQueueSupplier(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void send(String message) {
        rabbitTemplate.convertAndSend(exchanger, routingKey, message);
    }
}
