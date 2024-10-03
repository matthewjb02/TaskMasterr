package org.example.taskmasterr.presentation.dto;

import java.util.List;

public class PersonDTO {
    private long id;
    private String name;
    private List<Long> notes;

    public PersonDTO(long id, String name, List<Long> notes) {
        this.id = id;
        this.name = name;
        this.notes = notes;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Long> getNotes() {
        return notes;
    }



}
