package org.example.taskmasterr.presentation.dto;

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

    public long getId() {
        return id;

    }

    public String getContent() {
        return content;
    }

    public String getTitle() {
        return title;
    }

    public Long getPersonId() {
        return personId;
    }
}
