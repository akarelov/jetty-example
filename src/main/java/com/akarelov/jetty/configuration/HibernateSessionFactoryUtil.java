package com.akarelov.jetty.configuration;

import com.akarelov.jetty.domain.Author;
import com.akarelov.jetty.domain.Note;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HibernateSessionFactoryUtil {
    private static SessionFactory sessionFactory;
    private static Logger logger = LoggerFactory.getLogger(HibernateSessionFactoryUtil.class);

    private HibernateSessionFactoryUtil() {
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure();
                configuration.addAnnotatedClass(Author.class);
                configuration.addAnnotatedClass(Note.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());

            } catch (Exception e) {
                logger.info("Exception!!!" + e);
            }
        }
        return sessionFactory;
    }
}
