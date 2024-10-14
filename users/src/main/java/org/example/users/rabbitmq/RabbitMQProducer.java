package org.example.users.rabbitmq;

import org.example.commons.dto.NoteDTO;
import org.example.commons.dto.PersonDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RabbitMQProducer {

    @Value("${rabbit.exchange.name}")
    private String exchangeName;

    @Value("${rabbit.routing.key}")
    private String routingKey;

    @Value("${rabbit.note.routing.key}")
    private String noteRoutingKey;

    private final RabbitTemplate rabbitTemplate;

    public RabbitMQProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQProducer.class);

    public void sendUserMessage(PersonDTO person) {

        LOGGER.info("Sending message: {}", person);
        rabbitTemplate.convertAndSend(exchangeName, routingKey, person);
    }

    public List<NoteDTO> requestNotesByPersonId(Long personId) {
        return (List<NoteDTO>) rabbitTemplate.convertSendAndReceive(
                exchangeName, routingKey, personId);
    }

}