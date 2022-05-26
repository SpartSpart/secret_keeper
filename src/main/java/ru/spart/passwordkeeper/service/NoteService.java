package ru.spart.passwordkeeper.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.spart.passwordkeeper.controller.model.Note;
import ru.spart.passwordkeeper.controller.model.Secret;
import ru.spart.passwordkeeper.repository.NoteDataRepository;
import ru.spart.passwordkeeper.repository.SecretDataRepository;
import ru.spart.passwordkeeper.repository.UserDataRepository;
import ru.spart.passwordkeeper.repository.model.NoteData;
import ru.spart.passwordkeeper.repository.model.SecretData;
import ru.spart.passwordkeeper.repository.model.UserData;
import ru.spart.passwordkeeper.service.exception.NoteNotFound;
import ru.spart.passwordkeeper.service.exception.SecretNotFound;

import java.util.ArrayList;
import java.util.List;

@Service
public class NoteService {

    private final UserDataRepository userDataRepository;
    private final UserDetailsServiceImpl userDetailsServiceImpl;
    private final NoteDataRepository noteDataRepository;

    public NoteService(NoteDataRepository noteDataRepository, UserDataRepository userDataRepository, UserDetailsServiceImpl userDetailsServiceImpl) {
        this.noteDataRepository = noteDataRepository;
        this.userDataRepository = userDataRepository;
        this.userDetailsServiceImpl = userDetailsServiceImpl;
    }

    @Transactional
    public Long add(Note note) {
        NoteData noteData = new NoteData();
        noteData.setNote(note.getNote());
        noteData.setUserData(getUserData());

        noteDataRepository.saveAndFlush(noteData);
        return noteData.getId();
    }

    @Transactional
    public void update(long id, Note note) throws NoteNotFound {
        NoteData noteData = noteDataRepository.findByIdAndUserData(id, getUserData())
                .orElseThrow(NoteNotFound::new);

        noteData.setNote(note.getNote());

        noteDataRepository.saveAndFlush(noteData);
    }

    @Transactional(readOnly = true)
    public Note getNote(long id) throws NoteNotFound {
        NoteData noteData = noteDataRepository.findByIdAndUserData(id, getUserData())
                .orElseThrow(NoteNotFound::new);
        Note note = new Note();

        note.setId(noteData.getId());
        note.setNote(noteData.getNote());

        return note;
    }

    @Transactional
    public void deleteListNotes(List<Long> idList) throws NoteNotFound {
        for (Long id: idList)
            noteDataRepository.deleteById(id);
    }

    @Transactional
    public List<Note> getAllNotes() {
        List<Note> allNotes = new ArrayList<>();
        List<NoteData> allNotesData = noteDataRepository.findAllByUserData(getUserData());

        for (NoteData note : allNotesData) {
            Note newNote = new Note();
            newNote.setId(note.getId());
            newNote.setNote(note.getNote());

            allNotes.add(newNote);
        }

        return allNotes;
    }

    private UserData getUserData() {
        UserDetails userDetails = userDetailsServiceImpl.getCurrent();
        return userDataRepository.findByUserLogin(userDetails.getUsername())
                .orElseThrow(RuntimeException::new);
    }
}
