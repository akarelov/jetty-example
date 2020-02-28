package com.akarelov.jetty.service.impl;

import com.akarelov.jetty.dao.interfaces.NoteDao;
import com.akarelov.jetty.domain.Note;
import com.akarelov.jetty.exception.NoteNotExistsException;
import com.akarelov.jetty.service.interfaces.NoteService;
import com.google.inject.Inject;

import java.util.List;

public class NoteServiceImpl implements NoteService {
    private final NoteDao noteDao;
    private static final String NOTE_NOT_EXISTS = "note not exists";

    @Inject
    public NoteServiceImpl(NoteDao noteDao) {
        this.noteDao = noteDao;
    }

    @Override
    public List<Note> findAllByAuthorId(int id) {
        return noteDao.findAllByAuthorId(id);
    }

    @Override
    public Note add(Note note) {
        return noteDao.save(note);
    }

    @Override
    public Note update(Note note) {
        return noteDao.update(note);
    }

    @Override
    public Note delete(int id) {
        Note note = noteDao.findById(id)
                .orElseThrow(() -> new NoteNotExistsException(NOTE_NOT_EXISTS));
        return noteDao.delete(note);
    }

    @Override
    public List<Note> findAll() {
        return noteDao.findAll();
    }
}
