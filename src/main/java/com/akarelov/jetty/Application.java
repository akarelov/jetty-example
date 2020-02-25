package com.akarelov.jetty;

import com.akarelov.jetty.configuration.NonServletModuleConfiguration;
import com.akarelov.jetty.configuration.ServletModuleConfiguration;
import com.akarelov.jetty.dao.impl.AuthorDaoImpl;
import com.akarelov.jetty.dao.interfaces.AuthorDao;
import com.akarelov.jetty.domain.Author;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceFilter;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;

import javax.servlet.DispatcherType;
import java.util.Collections;
import java.util.EnumSet;

public class Application {
    public static void main(String[] args) throws Exception {
        NonServletModuleConfiguration nonServletModuleConfiguration = new NonServletModuleConfiguration();
        ServletModuleConfiguration moduleConfiguration = new ServletModuleConfiguration();
        Injector injector = Guice.createInjector(nonServletModuleConfiguration, moduleConfiguration);

        Server server = new Server(7070);
        ServletContextHandler handler = new ServletContextHandler(server, "/");
        handler.addFilter(GuiceFilter.class, "/*", EnumSet.allOf(DispatcherType.class));
        handler.addServlet(DefaultServlet.class, "/");
        server.start();

        AuthorDao authorDao = new AuthorDaoImpl();
        Author author = new Author();
        author.setFirstName("artem");
        author.setLastName("karelov");
        authorDao.save(author);
    }
}
