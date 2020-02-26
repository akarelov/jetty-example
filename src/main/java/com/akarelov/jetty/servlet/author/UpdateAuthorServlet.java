package com.akarelov.jetty.servlet.author;

import com.akarelov.jetty.dao.interfaces.AuthorDao;
import com.akarelov.jetty.domain.Author;
import com.akarelov.jetty.exception.UserNotExistsException.UserNotExistsException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.eclipse.jetty.http.HttpStatus;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
import java.util.stream.Collectors;

@Singleton
public class UpdateAuthorServlet extends HttpServlet {
    private final AuthorDao authorDao;
    private final ObjectMapper objectMapper;

    @Inject
    public UpdateAuthorServlet(AuthorDao authorDao, ObjectMapper objectMapper) {
        this.authorDao = authorDao;
        this.objectMapper = objectMapper;
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
            String response = objectMapper.writeValueAsString(update);
            resp.getWriter().println(response);
        } else {
            resp.addHeader("message", "user not exists");
            resp.setStatus(HttpStatus.BAD_REQUEST_400);
            throw new UserNotExistsException();
        }
    }
}
