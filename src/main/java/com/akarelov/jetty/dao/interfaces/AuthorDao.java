package com.akarelov.jetty.dao.interfaces;

import com.akarelov.jetty.domain.Author;

import java.util.List;

public interface AuthorDao {
    Author findById(int id);

    Author save(Author author);

    Author update(Author author);

    Author delete(Author author);

    List<Author> findAll();
}
