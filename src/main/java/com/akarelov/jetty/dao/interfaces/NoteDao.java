package com.akarelov.jetty.dao.interfaces;

import com.akarelov.jetty.domain.Note;

import java.util.List;
import java.util.Optional;

public interface NoteDao {
    Optional<Note> findById(int id);

    List<Note> findAllByAuthorId(int id);

    Note save(Note note);

    Note update(Note note);

    Note delete(Note note);

    List<Note> findAll();
}
