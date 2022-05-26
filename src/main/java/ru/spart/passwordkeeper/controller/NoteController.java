package ru.spart.passwordkeeper.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.spart.passwordkeeper.controller.model.Note;
import ru.spart.passwordkeeper.controller.model.Secret;
import ru.spart.passwordkeeper.service.NoteService;
import ru.spart.passwordkeeper.service.exception.NoteNotFound;

import java.util.List;

@RestController
@RequestMapping("/api")
public class NoteController {

    private final NoteService noteService;


    @Autowired
    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping(value = "/notes")
    public ResponseEntity<Long> addNote(@RequestBody Note note) {
        return ResponseEntity
                .ok()
                .body(noteService.add(note));
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping(value = "/notes/{id}")
    public ResponseEntity<Void> updateNote(@PathVariable("id") long id, @RequestBody Note note) {
        try {
            noteService.update(id, note);
        } catch (NoteNotFound noteNotFound) {
            throw new ApiNotFound();
        }
        return ResponseEntity.ok().build();
    }


    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/notes/{id}")
    public ResponseEntity<Note> getNote(@PathVariable("id") long id) {
        try {
            return ResponseEntity
                    .ok()
                    .body(noteService.getNote(id));
        } catch (NoteNotFound noteNotFound) {
            throw new ApiNotFound();
        }
    }


    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/notes")
    public ResponseEntity<List<Note>> getAllNotes() {
        return ResponseEntity
                .ok()
                .body(noteService.getAllNotes());
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping (value = "/notes/delete")
    public ResponseEntity<Void> deleteListNotes(@RequestBody List<Long> idList) {
        try {
            noteService.deleteListNotes(idList);
        } catch (NoteNotFound noteNotFound) {
            throw new ApiNotFound();
        }
        return ResponseEntity.ok().build();
    }

}
