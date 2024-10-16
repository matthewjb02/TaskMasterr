package org.example.taskmasterr.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Note {

    @Id
    private long id;

    private String title;

    private String text;

    @ManyToOne
    private Person person;

    public Note() {
    }

    public Note (long id, String title, String text, Person person) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.person = person;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public Person getPerson() {
        return person;
    }
}
