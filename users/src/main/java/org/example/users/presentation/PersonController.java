package org.example.users.presentation;

import org.example.users.application.PersonService;
import org.example.commons.dto.PersonDTO;
import org.example.users.rabbitmq.RabbitMQProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    private final PersonService personService;

    private final RabbitMQProducer rabbitMQProducer;

    private final RabbitMQProducer rabbitMQJsonProducer;

    public PersonController(PersonService personService, RabbitMQProducer rabbitMQProducer, RabbitMQProducer rabbitMQJsonProducer) {
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

}