package com.akarelov.jetty.servlet.note;

import com.akarelov.jetty.dao.interfaces.AuthorDao;
import com.akarelov.jetty.dao.interfaces.NoteDao;
import com.akarelov.jetty.domain.Note;
import com.akarelov.jetty.validation.ObjectValidator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.eclipse.jetty.http.HttpStatus;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

@Singleton
public class NoteServlet extends HttpServlet {
    private final NoteDao noteDao;
    private final AuthorDao authorDao;
    private final ObjectMapper objectMapper;
    private final ObjectValidator validator;

    @Inject
    public NoteServlet(NoteDao noteDao, AuthorDao authorDao, ObjectMapper objectMapper, ObjectValidator validator) {
        this.noteDao = noteDao;
        this.authorDao = authorDao;
        this.objectMapper = objectMapper;
        this.validator = validator;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String body = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        Note note = objectMapper.readValue(body, Note.class);
        Note noteFromDb = noteDao.save(note);
        resp.setContentType("json/application");
        resp.getWriter().println(objectMapper.writeValueAsString(noteFromDb));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendError(HttpStatus.BAD_REQUEST_400, "msg");
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }
}
