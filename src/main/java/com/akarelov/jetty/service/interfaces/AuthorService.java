package com.akarelov.jetty.service.interfaces;

import com.akarelov.jetty.domain.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    Optional<Author> findById(int id);

    Author add(Author author);

    Author update(Author author);

    Author delete(Author author);

    List<Author> findAll();
}
