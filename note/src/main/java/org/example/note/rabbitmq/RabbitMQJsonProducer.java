package org.example.note.rabbitmq;

import org.example.commons.dto.PersonDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQJsonProducer {

    @Value("${rabbit.exchange.name}")
    private String exchangeName;

    @Value("${rabbit.json.routing.key}")
    private String routingKey;

    private final RabbitTemplate rabbitTemplate;

    public RabbitMQJsonProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }


    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQProducer.class);


    public void sendUserMessage(PersonDTO person) {
        LOGGER.info("Sending message: {}", person);
        rabbitTemplate.convertAndSend(exchangeName, routingKey, person);
    }
}