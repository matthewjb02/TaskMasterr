package org.example.users.presentation;

import org.example.commons.dto.NoteDTO;
import org.example.users.application.PersonService;
import org.example.commons.dto.PersonDTO;
import org.example.users.rabbitmq.RabbitMQJsonProducer;
import org.example.users.rabbitmq.RabbitMQProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    private final PersonService personService;

    private final RabbitMQProducer rabbitMQProducer;

    private final RabbitMQJsonProducer rabbitMQJsonProducer;

    public PersonController(PersonService personService, RabbitMQProducer rabbitMQProducer, RabbitMQJsonProducer rabbitMQJsonProducer) {
        this.personService = personService;
        this.rabbitMQProducer = rabbitMQProducer;
        this.rabbitMQJsonProducer = rabbitMQJsonProducer;
    }

    @PostMapping
    public void addPerson(@RequestBody PersonDTO personDTO) {
        personService.addPerson(personDTO);
    }

    @GetMapping("/{id}")
    public PersonDTO getPersonById(@PathVariable Long id) {
        return personService.getPersonById(id);
    }

    @GetMapping
    public List<PersonDTO> getAllPersons() {
        return personService.getAllPersons();
    }

    // rabbitmq
    @GetMapping("/publish")
    public ResponseEntity<String> sendMessage(@RequestParam String message) {
        rabbitMQProducer.sendMessage(message);
        return ResponseEntity.ok("Message sent to the RabbitMQ JavaInUse Successfully");
    }

    @PostMapping("/publish_json")
    public ResponseEntity<String> sendMessageJson(@RequestBody PersonDTO personDTO) {
         rabbitMQJsonProducer.sendUserMessage(personDTO);
         return ResponseEntity.ok("Message sent to the RabbitMQ JavaInUse Successfully");
    }

}