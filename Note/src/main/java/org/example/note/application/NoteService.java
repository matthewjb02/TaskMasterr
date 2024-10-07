package org.example.note.application;


import jakarta.transaction.Transactional;
import org.example.note.data.NoteRepository;
import org.example.note.domain.Note;
import org.example.note.presentation.dto.NoteDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Transactional
@Service
public class NoteService {

    private NoteRepository noteRepository;

    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public NoteDTO noteIntoDTO(Note note) {
        return new NoteDTO(note.getId(), note.getTitle(), note.getText());
    }

    public Note dtoIntoNote(NoteDTO dto) {
        return new Note(dto.getId(), dto.getTitle(), dto.getContent());
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

