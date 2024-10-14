package org.example.users.application;

import jakarta.transaction.Transactional;
import org.example.commons.dto.NoteDTO;
import org.example.commons.dto.PersonDTO;
import org.example.users.data.PersonRepository;
import org.example.users.domain.Person;
import org.example.users.rabbitmq.RabbitMQProducer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;


@Transactional
@Service
public class PersonService {

    private final PersonRepository personRepository;
    private final RestTemplate restTemplate;
    private final RabbitMQProducer rabbitMQProducer;
    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQProducer.class);


    @Autowired
    public PersonService(PersonRepository personRepository, RestTemplate restTemplate, RabbitMQProducer rabbitMQProducer) {
        this.personRepository = personRepository;
        this.restTemplate = restTemplate;
        this.rabbitMQProducer = rabbitMQProducer;
    }

    public List<Long> getNotesByPersonId(Long personId) {

        LOGGER.info("Sending request for notes by personId: {}", personId);

        List<NoteDTO> notes = rabbitMQProducer.requestNotesByPersonId(personId);
        return notes.stream().map(NoteDTO::getId).collect(Collectors.toList());
    }


    public PersonDTO personIntoDTO(Person person) {
        List<Long> notes = getNotesByPersonId(person.getId());
        return new PersonDTO(person.getId(), person.getName(), notes);
    }

    public Person dtoIntoPerson(PersonDTO dto) {
        List<NoteDTO> noteDTOs = new ArrayList<>();
        for (Long noteId : dto.getNotes()) {
            String url = "http://localhost:8082/note/" + noteId;
            NoteDTO noteDTO = restTemplate.getForObject(url, NoteDTO.class);
            noteDTOs.add(noteDTO);
        }
        return new Person(dto.getId(), dto.getName(), noteDTOs);
    }

    public void addPerson(PersonDTO personDTO) {
        Person person = dtoIntoPerson(personDTO);
        personRepository.save(person);
    }

    public PersonDTO getPersonById(Long id) {
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Person not found with id: " + id));
        return personIntoDTO(person);
    }

    public List<PersonDTO> getAllPersons() {
        List<Person> persons = personRepository.findAll();
        return persons.stream().map(this::personIntoDTO).collect(Collectors.toList());
    }

}