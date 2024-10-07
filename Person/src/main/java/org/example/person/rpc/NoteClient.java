package org.example.person.rpc;


import org.example.commons.dto.NoteDTO;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class NoteClient {

    private final RestTemplate restTemplate;
    private final String noteServiceUrl;

    public NoteClient(String noteServiceUrl) {
        this.restTemplate = new RestTemplate();
        this.noteServiceUrl = noteServiceUrl;
    }

    public NoteDTO getNoteById(Long id) {
        return restTemplate.getForObject(noteServiceUrl + "/note/" + id, NoteDTO.class);
    }

    public List<NoteDTO> getAllNotes() {
        return restTemplate.getForObject(noteServiceUrl + "/note", List.class);
    }
}