package com.akarelov.jetty.servlet.author;

import com.akarelov.jetty.dao.interfaces.AuthorDao;
import com.akarelov.jetty.domain.Author;
import com.akarelov.jetty.exception.UserNotExistsException;
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

@Singleton
public class AuthorServlet extends HttpServlet {
    private final AuthorDao authorDao;
    private final ObjectMapper objectMapper;

    @Inject
    public AuthorServlet(AuthorDao authorDao, ObjectMapper objectMapper) {
        this.authorDao = authorDao;
        this.objectMapper = objectMapper;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String body = req.getReader()
                .lines()
                .collect(Collectors.joining(System.lineSeparator()));
        Author author = objectMapper.readValue(body, Author.class);
        Author save = authorDao.save(author);
        resp.setContentType("json/application");
        String response = objectMapper.writeValueAsString(save);
        resp.getWriter().println(response);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String body = req.getReader()
                .lines()
                .collect(Collectors.joining(System.lineSeparator()));
        Author author = objectMapper.readValue(body, Author.class);
        Optional<Author> authFromDb = authorDao.findById(author.getId());
        if (authFromDb.isPresent()) {
            Author delete = authorDao.delete(authFromDb.get());
            resp.setContentType("json/application");
            String response = objectMapper.writeValueAsString(delete);
            resp.getWriter().println(response);
        } else {
            resp.addHeader("message", "user not exists");
            resp.addHeader("errorCode", String.valueOf(HttpStatus.BAD_REQUEST_400));
            throw new UserNotExistsException();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idString = req.getParameter("id");
        if (idString == null) {
            List<Author> authors = authorDao.findAll();
            resp.setContentType("json/application");
            resp.getWriter().write(objectMapper.writeValueAsString(authors));
            return;
        }
        int id = -1;
        try {
            id = Integer.parseInt(idString.trim());
        } catch (NumberFormatException ex) {
            resp.addHeader("message", "you passed not number parameter");
            resp.addHeader("errorCode", String.valueOf(HttpStatus.BAD_REQUEST_400));
        }

        Optional<Author> authFromDb = authorDao.findById(id);
        if (authFromDb.isPresent()) {
            resp.setContentType("json/application");
            String response = objectMapper.writeValueAsString(authFromDb.get());
            resp.getWriter().println(response);
        } else {
            resp.addHeader("message", "user not exists");
            resp.addHeader("errorCode", String.valueOf(HttpStatus.BAD_REQUEST_400));
            throw new UserNotExistsException();
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
            resp.setContentType("json/application");
            String response = objectMapper.writeValueAsString(update);
            resp.getWriter().println(response);
        } else {
            resp.addHeader("message", "user not exists");
            resp.setStatus(HttpStatus.BAD_REQUEST_400);
            throw new UserNotExistsException();
        }
    }
}
