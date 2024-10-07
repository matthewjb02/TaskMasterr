package org.example.person.presentation;


import org.example.person.application.PersonService;
import org.example.person.presentation.dto.PersonDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
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
}