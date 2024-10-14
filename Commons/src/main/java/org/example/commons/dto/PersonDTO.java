package org.example.commons.dto;

import lombok.Data;

import java.util.List;


@Data

public class PersonDTO {
    private long id;
    private String name;
    private List<Long> notes;

    // No-argument constructor
    public PersonDTO() {
    }

    public PersonDTO(long id, String name, List<Long> notes) {
        this.id = id;
        this.name = name;
        this.notes = notes;
    }
}