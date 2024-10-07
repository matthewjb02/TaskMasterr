package org.example.commons.dto;

import lombok.Getter;

@Getter
public class NoteDTO {

    private long id;
    private String title;
    private String content;

    public NoteDTO(long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

}
