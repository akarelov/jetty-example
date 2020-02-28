package com.akarelov.jetty.service.impl;

import com.akarelov.jetty.dao.interfaces.AuthorDao;
import com.akarelov.jetty.domain.Author;
import com.akarelov.jetty.exception.UserNotExistsException;
import com.akarelov.jetty.service.interfaces.AuthorService;
import com.google.inject.Inject;

import java.util.List;

public class AuthorServiceImpl implements AuthorService {
    private final AuthorDao authorDao;
    private final static String USER_NOT_EXISTS = "user not exists";

    @Inject
    public AuthorServiceImpl(AuthorDao authorDao) {
        this.authorDao = authorDao;
    }

    @Override
    public Author findById(int id) {
        return authorDao
                .findById(id)
                .orElseThrow(() -> new UserNotExistsException(USER_NOT_EXISTS));
    }

    @Override
    public Author add(Author author) {
        return authorDao.save(author);
    }

    @Override
    public Author update(Author author) {
        return authorDao.save(author);
    }

    @Override
    public Author delete(int id) {
        Author authorFromDb = authorDao.findById(id)
                .orElseThrow(() -> new UserNotExistsException(USER_NOT_EXISTS));
        return authorDao.delete(authorFromDb);
    }

    @Override
    public List<Author> findAll() {
        return authorDao.findAll();
    }
}
