package org.example.note.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Note {

    @Id
    private long id;

    private String title;

    private String text;


    private long personId;

    public Note() {
    }

    public Note(long id, String title, String text, long personId) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.personId = personId;
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

    public long getPersonId() {
        return personId;
    }
}