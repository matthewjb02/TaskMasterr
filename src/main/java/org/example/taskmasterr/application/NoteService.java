package org.example.taskmasterr.application;

import jakarta.transaction.Transactional;
import org.example.taskmasterr.data.NoteRepository;
import org.example.taskmasterr.data.PersonRepository;
import org.example.taskmasterr.domain.Note;
import org.example.taskmasterr.domain.Person;
import org.example.taskmasterr.presentation.dto.NoteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Transactional
@Service
public class NoteService {

    private final NoteRepository noteRepository;
    private final PersonRepository personRepository;


    @Autowired
    public NoteService(NoteRepository noteRepository, PersonRepository personRepository) {
        this.noteRepository = noteRepository;
        this.personRepository = personRepository;
    }

    public NoteDTO noteIntoDTO(Note note) {
        return new NoteDTO(note.getId(), note.getTitle(), note.getText(), note.getPerson().getId());
    }

    public Note dtoIntoNote(NoteDTO dto) {
        Person person  = personRepository.findById(dto.getPersonId())
                .orElseThrow(() -> new NoSuchElementException("Person not found with id: " + dto.getPersonId()));
        return new Note(dto.getId(), dto.getTitle(), dto.getContent(), person);
    }

    public NoteDTO findById(long id) {
        Note note = noteRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Note not found with id: " + id));
        return noteIntoDTO(note);
    }

    public void addNote(NoteDTO noteDTO) {
        Note note = dtoIntoNote(noteDTO);
        Person person = note.getPerson();
        person.getNotes().add(note);
        noteRepository.save(note);
        personRepository.save(person);

    }

    public NoteDTO getNoteById(long id) {
        Note note = noteRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Note not found with id: " + id));
        return noteIntoDTO(note);
    }

    public List<NoteDTO> getAllNotes() {
        return noteRepository.findAll().stream()
                .map(this::noteIntoDTO)
                .collect(Collectors.toList());
    }
}