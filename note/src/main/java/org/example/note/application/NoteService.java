package org.example.note.application;

import jakarta.transaction.Transactional;
import org.example.commons.dto.NoteDTO;
import org.example.commons.dto.PersonDTO;
import org.example.note.data.NoteRepository;
import org.example.note.domain.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Transactional
@Service
public class NoteService {

    private final RestTemplate restTemplate;
    private final NoteRepository noteRepository;

    @Autowired
    public NoteService(RestTemplate restTemplate, NoteRepository noteRepository) {
        this.restTemplate = restTemplate;
        this.noteRepository = noteRepository;
    }

    public NoteDTO noteIntoDTO(Note note) {
        return new NoteDTO(note.getId(), note.getTitle(), note.getText(), note.getPersonId());
    }

    public Note dtoIntoNote(NoteDTO dto) {
        return new Note(dto.getId(), dto.getTitle(), dto.getContent(), dto.getPersonId());
    }

    public NoteDTO findById(long id) {
        Note note = noteRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Note not found with id: " + id));
        return noteIntoDTO(note);
    }

    public void addNote(NoteDTO noteDTO) {
        Note note = dtoIntoNote(noteDTO);

        noteRepository.save(note);

    }

    public List<NoteDTO> getAllNotes() {
        return noteRepository.findAll().stream()
                .map(this::noteIntoDTO)
                .collect(Collectors.toList());
    }

    public List<NoteDTO> getNotesByPersonId(Long personId) {
        return noteRepository.findByPersonId(personId).stream()
                .map(this::noteIntoDTO)
                .collect(Collectors.toList());
    }

    public NoteDTO getNoteById(Long id) {
        return findById(id);
    }
}