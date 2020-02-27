package com.akarelov.jetty.servlet.author;

import com.akarelov.jetty.dao.interfaces.AuthorDao;
import com.akarelov.jetty.domain.Author;
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
public class AddAuthorServlet extends HttpServlet {
    private final AuthorDao authorDao;
    private final ObjectMapper objectMapper;

    @Inject
    public AddAuthorServlet(AuthorDao authorDao, ObjectMapper objectMapper) {
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
}
