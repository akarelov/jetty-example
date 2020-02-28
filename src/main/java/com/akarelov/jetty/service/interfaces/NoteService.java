package com.akarelov.jetty.service.interfaces;

import com.akarelov.jetty.domain.Note;

import java.util.List;

public interface NoteService {
    List<Note> findAllByAuthorId(int id);

    Note add(Note note);

    Note update(Note note);

    Note delete(int id);

    List<Note> findAll();
}
