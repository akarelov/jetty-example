package com.akarelov.jetty.servlet.note;

import com.akarelov.jetty.dao.interfaces.NoteDao;
import com.akarelov.jetty.domain.Note;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

@Singleton
public class AddNoteServlet extends HttpServlet {
    private final NoteDao noteDao;
    private final ObjectMapper objectMapper;

    @Inject
    public AddNoteServlet(NoteDao noteDao, ObjectMapper objectMapper) {
        this.noteDao = noteDao;
        this.objectMapper = objectMapper;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String body = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        Note note = objectMapper.readValue(body, Note.class);
        Note noteFromDb = noteDao.save(note);
        resp.setContentType("json/application");
        resp.getWriter().println(objectMapper.writeValueAsString(noteFromDb));
    }
}
