package com.akarelov.jetty.servlet.author;

import com.akarelov.jetty.dao.interfaces.AuthorDao;
import com.akarelov.jetty.domain.Author;
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
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.eclipse.jetty.http.MimeTypes.Type.APPLICATION_JSON;

@Singleton
public class AuthorServlet extends HttpServlet {
    private final AuthorDao authorDao;
    private final ObjectMapper objectMapper;
    private final static String USER_NOT_EXISTS = "user not exists";
    private final ObjectValidator validator;

    @Inject
    public AuthorServlet(AuthorDao authorDao, ObjectMapper objectMapper, ObjectValidator validator) {
        this.authorDao = authorDao;
        this.objectMapper = objectMapper;
        this.validator = validator;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String body = req.getReader()
                .lines()
                .collect(Collectors.joining(System.lineSeparator()));
        Author author = objectMapper.readValue(body, Author.class);
        Author save = authorDao.save(author);
        resp.setContentType(APPLICATION_JSON.asString());
        String response = objectMapper.writeValueAsString(save);
        resp.getWriter().println(response);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = validator.parseInt(req.getParameter("id"), resp);
        Optional<Author> authorFromDb = authorDao.findById(id);
        if (authorFromDb.isPresent()) {
            Author delete = authorDao.delete(authorFromDb.get());
            resp.setContentType(APPLICATION_JSON.asString());
            String response = objectMapper.writeValueAsString(delete);
            resp.getWriter().println(response);
        } else {
            resp.sendError(HttpStatus.BAD_REQUEST_400, USER_NOT_EXISTS);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idString = req.getParameter("id");
        if (idString == null) {
            List<Author> authors = authorDao.findAll();
            resp.setContentType(APPLICATION_JSON.asString());
            resp.getWriter().write(objectMapper.writeValueAsString(authors));
            return;
        }
        int id = validator.parseInt(idString, resp);
        Optional<Author> authFromDb = authorDao.findById(id);
        if (authFromDb.isPresent()) {
            resp.setContentType(APPLICATION_JSON.asString());
            String response = objectMapper.writeValueAsString(authFromDb.get());
            resp.getWriter().println(response);
        } else {
            resp.sendError(HttpStatus.BAD_REQUEST_400, USER_NOT_EXISTS);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String body = req.getReader()
                .lines()
                .collect(Collectors.joining(System.lineSeparator()));
        Author author = objectMapper.readValue(body, Author.class);
        Optional<Author> authFromDb = authorDao.findById(author.getId());
        if (authFromDb.isPresent()) {
            Author update = authorDao.update(author);
            resp.setContentType(APPLICATION_JSON.asString());
            String response = objectMapper.writeValueAsString(update);
            resp.getWriter().println(response);
        } else {
            resp.sendError(HttpStatus.BAD_REQUEST_400, USER_NOT_EXISTS);
        }
    }
}
