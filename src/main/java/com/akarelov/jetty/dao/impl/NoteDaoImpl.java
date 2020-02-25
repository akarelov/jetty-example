package com.akarelov.jetty.dao.impl;

import com.akarelov.jetty.dao.interfaces.NoteDao;
import com.akarelov.jetty.domain.Note;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

import static com.akarelov.jetty.configuration.HibernateSessionFactoryUtil.getSessionFactory;

public class NoteDaoImpl implements NoteDao {
    @Override
    public Note findById(int id) {
        try (Session session = getSessionFactory().openSession()) {
            return session.get(Note.class, id);
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
