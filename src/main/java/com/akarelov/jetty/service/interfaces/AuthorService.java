package com.akarelov.jetty.service.interfaces;

import com.akarelov.jetty.domain.Author;

import java.util.List;

public interface AuthorService {
    Author findById(int id);

    Author add(Author author);

    Author update(Author author);

    Author delete(Author author);

    List<Author> findAll();
}
