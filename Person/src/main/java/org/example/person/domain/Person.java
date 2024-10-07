package org.example.person.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import org.example.commons.dto.NoteDTO;
import org.example.person.rpc.NoteClient;

import java.util.ArrayList;
import java.util.List;

public class Person {
    private long id;
    private String name;
    private List<NoteDTO> notes;
    private NoteClient noteClient;

    public Person(long id, String name, NoteClient noteClient) {
        this.id = id;
        this.name = name;
        this.noteClient = noteClient;
        this.notes = new ArrayList<>();
    }

    public void addNoteById(long noteId) {
        NoteDTO note = noteClient.getNoteById(noteId);
        notes.add(note);
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<NoteDTO> getNotes() {
        return notes;
    }
}