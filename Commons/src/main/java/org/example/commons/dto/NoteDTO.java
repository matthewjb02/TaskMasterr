package org.example.commons.dto;

import lombok.Data;

@Data
public class NoteDTO{

    private long id;
    private String title;
    private String content;
    private Long personId;

    public NoteDTO() {
    }

    public NoteDTO(long id, String title, String content, Long personId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.personId = personId;
    }
}
