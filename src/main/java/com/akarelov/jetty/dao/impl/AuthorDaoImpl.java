package com.akarelov.jetty.dao.impl;

import com.akarelov.jetty.dao.interfaces.AuthorDao;
import com.akarelov.jetty.domain.Author;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

import static com.akarelov.jetty.configuration.HibernateSessionFactoryUtil.getSessionFactory;

public class AuthorDaoImpl implements AuthorDao {

    @Override
    public Author findById(int id) {
        try (Session session = getSessionFactory().openSession()) {
            return session.get(Author.class, id);
        }
    }

    @Override
    public Author save(Author author) {
        try (Session session = getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(author);
            transaction.commit();
            return author;
        }
    }

    @Override
    public Author update(Author author) {
        try (Session session = getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(author);
            transaction.commit();
            return author;
        }
    }

    @Override
    public Author delete(Author author) {
        try (Session session = getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(author);
            transaction.commit();
            return author;
        }
    }

    @Override
    public List<Author> findAll() {
        try (Session session = getSessionFactory().openSession()) {
            return (List<Author>) session
                    .createQuery("from " + Author.class).list();
        }
    }
}
