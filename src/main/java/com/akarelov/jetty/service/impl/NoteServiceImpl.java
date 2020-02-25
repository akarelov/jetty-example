package com.akarelov.jetty.service.impl;

import com.akarelov.jetty.dao.interfaces.NoteDao;
import com.akarelov.jetty.domain.Note;
import com.akarelov.jetty.service.interfaces.NoteService;
import com.google.inject.Inject;

import java.util.List;

public class NoteServiceImpl implements NoteService {
    private final NoteDao noteDao;

    @Inject
    public NoteServiceImpl(NoteDao noteDao) {
        this.noteDao = noteDao;
    }

    @Override
    public Note findById(int id) {
        return noteDao.findById(id);
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
    public Note delete(Note note) {
        return noteDao.delete(note);
    }

    @Override
    public List<Note> findAll() {
        return noteDao.findAll();
    }
}
