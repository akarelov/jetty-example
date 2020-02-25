package com.akarelov.jetty.dao.interfaces;

import com.akarelov.jetty.domain.Note;

import java.util.List;

public interface NoteDao {
    Note findById(int id);

    Note save(Note note);

    Note update(Note note);

    Note delete(Note note);

    List<Note> findAll();
}
