package com.akarelov.jetty.dao.impl;

import com.akarelov.jetty.dao.interfaces.NoteDao;
import com.akarelov.jetty.domain.Note;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;

import static com.akarelov.jetty.configuration.HibernateSessionFactoryUtil.getSessionFactory;

public class NoteDaoImpl implements NoteDao {
    @Override
    public Optional<Note> findById(int id) {
        try (Session session = getSessionFactory().openSession()) {
            Note note = session.get(Note.class, id);
            if (note == null) {
                return Optional.empty();
            }
            return Optional.of(note);
        }
    }

    @Override
    public List<Note> findAllByAuthorId(int id) {
        try (Session session = getSessionFactory().openSession()) {
            String query = "from Note n where n.author_id = :author_id";
            return (List<Note>) session.createQuery(query).setParameter("author_id", id).list();
        }
    }

    @Override
    public Note save(Note note) {
        try (Session session = getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(note);
            transaction.commit();
            return note;
        }
    }

    @Override
    public Note update(Note note) {
        try (Session session = getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(note);
            transaction.commit();
            return note;
        }
    }

    @Override
    public Note delete(Note note) {
        try (Session session = getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(note);
            transaction.commit();
            return note;
        }
    }

    @Override
    public List<Note> findAll() {
        try (Session session = getSessionFactory().openSession()) {
            return (List<Note>) session
                    .createQuery("from " + Note.class).list();
        }
    }
}
