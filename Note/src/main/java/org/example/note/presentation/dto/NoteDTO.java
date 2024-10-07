package org.example.note.presentation.dto;

public class NoteDTO{

    private long id;
    private String title;
    private String content;

    public NoteDTO() {
    }

    public NoteDTO(long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
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

}
