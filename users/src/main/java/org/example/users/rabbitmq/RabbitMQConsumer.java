package org.example.users.rabbitmq;


import org.aspectj.weaver.ast.Not;
import org.example.commons.dto.NoteDTO;
import org.example.commons.dto.PersonDTO;
import org.slf4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQConsumer {


    private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(RabbitMQConsumer.class);

    @RabbitListener(queues = "${rabbit.note.queue.name}")
    public void consumeNoteMessage(NoteDTO note) {
        LOGGER.info("Message received: {}", note);
    }


}
