package org.example.users.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import org.example.commons.dto.NoteDTO;

import java.util.List;

@Entity
@JsonSerialize
public class Person {

    @Id
    private long id;
    private String name;

    @Transient
    private List<NoteDTO> notes;


    public Person() {
    }

    public Person(long id, String name, List<NoteDTO> notes) {
        this.id = id;
        this.name = name;
        this.notes = notes;
    }

    public void addNote(NoteDTO note) {
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