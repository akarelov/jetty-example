package com.akarelov.jetty.dao.interfaces;

import com.akarelov.jetty.domain.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorDao {
    Optional<Author> findById(int id);

    Author save(Author author);

    Author update(Author author);

    Author delete(Author author);

    List<Author> findAll();
}
