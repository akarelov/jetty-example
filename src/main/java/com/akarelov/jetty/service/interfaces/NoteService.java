package com.akarelov.jetty.service.interfaces;

import com.akarelov.jetty.domain.Note;

import java.util.List;

public interface NoteService {
    Note findById(int id);

    Note add(Note note);

    Note update(Note note);

    Note delete(Note note);

    List<Note> findAll();
}
