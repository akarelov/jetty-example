package com.akarelov.jetty.service.impl;

import com.akarelov.jetty.dao.interfaces.AuthorDao;
import com.akarelov.jetty.domain.Author;
import com.akarelov.jetty.service.interfaces.AuthorService;
import com.google.inject.Inject;

import java.util.List;
import java.util.Optional;

public class AuthorServiceImpl implements AuthorService {
    private final AuthorDao authorDao;

    @Inject
    public AuthorServiceImpl(AuthorDao authorDao) {
        this.authorDao = authorDao;
    }

    @Override
    public Optional<Author> findById(int id) {
        return authorDao.findById(id);
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
    public Author delete(Author author) {
        return authorDao.delete(author);
    }

    @Override
    public List<Author> findAll() {
        return authorDao.findAll();
    }
}
