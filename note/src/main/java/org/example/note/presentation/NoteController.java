package org.example.note.presentation;


import org.example.commons.dto.NoteDTO;
import org.example.note.application.NoteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/note")
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @PostMapping
    public void addNote(@RequestBody NoteDTO noteDTO) {
        noteService.addNote(noteDTO);
    }

    @GetMapping("/{id}")
    public NoteDTO getNoteById(@PathVariable Long id) {
        return noteService.getNoteById(id);
    }

    @GetMapping
    public List<NoteDTO> getAllNotes() {
        return noteService.getAllNotes();
    }

    // rpc
    @GetMapping("/person/{personId}")
    public ResponseEntity<List<NoteDTO>> getNotesByPersonId(@PathVariable Long personId) {
        List<NoteDTO> notes = noteService.getNotesByPersonId(personId);
        return ResponseEntity.ok(notes);
    }

    // messaging


}