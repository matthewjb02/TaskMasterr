package org.example.note.rabbitmq;


import org.example.commons.dto.PersonDTO;
import org.slf4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQJsonConsumer {


    private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(RabbitMQConsumer.class);

    @RabbitListener(queues = "${rabbit.json.queue.name}")
    public void consumeJsonMessage(PersonDTO person) {
        LOGGER.info("Message received: {}", person);
    }


}
