package org.example.taskmasterr.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Person {

    @Id
    private long id;
    private String name;

    @OneToMany
    private List<Note> notes;

    public Person() {

    }

    public Person(long id, String name, List<Note> notes) {
        this.id = id;
        this.name = name;
        this.notes = notes;
    }

    public  void addNote (Note note) {
        notes.add(note);
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Note> getNotes() {
        return notes;
    }
}
