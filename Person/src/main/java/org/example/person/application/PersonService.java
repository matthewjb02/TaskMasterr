package org.example.person.application;


import jakarta.transaction.Transactional;
import org.example.person.data.PersonRepository;
import org.example.person.domain.Person;
import org.example.person.presentation.dto.PersonDTO;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Transactional
@Service
public class PersonService {

    private PersonRepository personRepository;
    private NoteService noteService;

    public PersonService(PersonRepository personRepository, NoteService noteService) {
        this.personRepository = personRepository;
        this.noteService = noteService;
    }

    public PersonDTO personIntoDTO(Person person) {
        List<Long> notes = person.getNotes().stream().map(Note::getId).collect(Collectors.toList());
        return new PersonDTO(person.getId(), person.getName(), notes);
    }

    public Person dtoIntoPerson(PersonDTO dto) {
        List<Note> notes = new ArrayList<>();
        for (Long n : dto.getNotes()) {
            notes.add(noteService.dtoIntoNote(noteService.findById(n)));
        }
        return new Person(dto.getId(), dto.getName(), notes);
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
        return personRepository.findAll().stream()
                .map(this::personIntoDTO)
                .collect(Collectors.toList());
    }
}
