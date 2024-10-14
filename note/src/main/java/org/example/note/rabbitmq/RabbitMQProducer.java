package org.example.note.rabbitmq;

import org.example.commons.dto.NoteDTO;
import org.example.commons.dto.PersonDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQProducer {

    @Value("${rabbit.exchange.name}")
    private String exchangeName;

    @Value("${rabbit.routing.key}")
    private String routingKey;

    private final RabbitTemplate rabbitTemplate;

    public RabbitMQProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }


    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQProducer.class);

    public void sendUserMessage(NoteDTO note) {
        LOGGER.info("Sending message: {}", note);
        rabbitTemplate.convertAndSend(exchangeName, routingKey, note);
    }
}