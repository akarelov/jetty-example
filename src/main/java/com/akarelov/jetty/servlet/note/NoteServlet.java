package com.akarelov.jetty.servlet.note;

import com.akarelov.jetty.domain.Note;
import com.akarelov.jetty.service.interfaces.NoteService;
import com.akarelov.jetty.validation.ObjectValidator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static org.eclipse.jetty.http.MimeTypes.Type.APPLICATION_JSON;

@Singleton
public class NoteServlet extends HttpServlet {
    private final ObjectMapper objectMapper;
    private final ObjectValidator validator;
    private final NoteService noteService;

    @Inject
    public NoteServlet(ObjectMapper objectMapper, ObjectValidator validator, NoteService noteService) {
        this.objectMapper = objectMapper;
        this.validator = validator;
        this.noteService = noteService;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String body = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        Note note = objectMapper.readValue(body, Note.class);
        validator.isValidate(note);
        Note addedNote = noteService.add(note);
        resp.setContentType(APPLICATION_JSON.asString());
        resp.getWriter().println(objectMapper.writeValueAsString(addedNote));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String authorId = req.getParameter("authorId");
        if (authorId == null) {
            List<Note> notes = noteService.findAll();
            resp.getWriter().write(objectMapper.writeValueAsString(notes));
            return;
        }
        int id = validator.parseStringToInt(authorId);
        List<Note> allNotesByAuthor = noteService.findAllByAuthorId(id);
        resp.getWriter().write(objectMapper.writeValueAsString(allNotesByAuthor));
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String body = req.getReader()
                .lines()
                .collect(Collectors.joining(System.lineSeparator()));
        Note note = objectMapper.readValue(body, Note.class);
        validator.isValidate(note);
        Note updatedNote = noteService.update(note);
        resp.getWriter().write(objectMapper.writeValueAsString(updatedNote));
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String noteId = req.getParameter("noteId");
        int id = validator.parseStringToInt(noteId);
        Note deletedNote = noteService.delete(id);
        resp.getWriter().write(objectMapper.writeValueAsString(deletedNote));
    }
}
