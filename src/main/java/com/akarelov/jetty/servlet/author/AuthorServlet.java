package com.akarelov.jetty.servlet.author;

import com.akarelov.jetty.domain.Author;
import com.akarelov.jetty.service.interfaces.AuthorService;
import com.akarelov.jetty.validation.ObjectValidator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static org.eclipse.jetty.http.MimeTypes.Type.APPLICATION_JSON;

@Singleton
public class AuthorServlet extends HttpServlet {
    private final ObjectMapper objectMapper;
    private final ObjectValidator validator;
    private final AuthorService authorService;

    @Inject
    public AuthorServlet(ObjectMapper objectMapper, ObjectValidator validator, AuthorService authorService) {
        this.objectMapper = objectMapper;
        this.validator = validator;
        this.authorService = authorService;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType(APPLICATION_JSON.asString());
        String body = req.getReader()
                .lines()
                .collect(Collectors.joining(System.lineSeparator()));
        Author requestAuthor = objectMapper.readValue(body, Author.class);
        Author responseAuthor = authorService.add(requestAuthor);
        resp.getWriter().println(objectMapper.writeValueAsString(responseAuthor));
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType(APPLICATION_JSON.asString());
        int id = validator.parseStringToInt(req.getParameter("id"));
        Author delete = authorService.delete(id);
        resp.getWriter().println(objectMapper.writeValueAsString(delete));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType(APPLICATION_JSON.asString());
        String idString = req.getParameter("id");
        if (idString.isEmpty()) {
            List<Author> authors = authorService.findAll();
            resp.getWriter().write(objectMapper.writeValueAsString(authors));
            return;
        }
        int id = validator.parseStringToInt(idString);
        Author author = authorService.findById(id);
        resp.getWriter().write(objectMapper.writeValueAsString(author));
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType(APPLICATION_JSON.asString());
        String body = req.getReader()
                .lines()
                .collect(Collectors.joining(System.lineSeparator()));
        Author author = objectMapper.readValue(body, Author.class);
        Author updatedAuthor = authorService.update(author);
        resp.getWriter().write(objectMapper.writeValueAsString(updatedAuthor));
    }
}
