package org.example.note.rabbitmq;


import org.example.commons.dto.NoteDTO;
import org.example.note.application.NoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RabbitMQConsumer {

    private final NoteService noteService;

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQProducer.class);

    @Autowired
    public RabbitMQConsumer(NoteService noteService) {
        this.noteService = noteService;
    }

    @RabbitListener(queues = "${rabbit.person.queue.name}")
    public List<NoteDTO> consumeNotesByPersonId(Long personId) {
        LOGGER.info("Received request for notes by personId: {}", personId);

        List<NoteDTO> notes = noteService.getNotesByPersonId(personId);

        return notes;
    }
}