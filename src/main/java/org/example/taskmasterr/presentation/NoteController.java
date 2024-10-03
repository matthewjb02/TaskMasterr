package org.example.taskmasterr.presentation;

import org.example.taskmasterr.application.NoteService;
import org.example.taskmasterr.presentation.dto.NoteDTO;
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
}