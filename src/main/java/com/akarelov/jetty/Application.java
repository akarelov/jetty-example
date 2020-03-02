package com.akarelov.jetty;

import com.akarelov.jetty.configuration.ErrorHandler;
import com.akarelov.jetty.configuration.NonServletModuleConfiguration;
import com.akarelov.jetty.configuration.ServletModuleConfiguration;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceFilter;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;

import javax.servlet.DispatcherType;
import java.util.EnumSet;

public class Application {
    public static void main(String[] args) throws Exception {
        ServletModuleConfiguration moduleConfiguration = new ServletModuleConfiguration();
        Injector injector = Guice.createInjector(NonServletModuleConfiguration.getInstance(), moduleConfiguration);

        Server server = new Server(7070);
        ServletContextHandler handler = new ServletContextHandler(server, "/");
        handler.setErrorHandler(new ErrorHandler());
        handler.addFilter(GuiceFilter.class, "/*", EnumSet.allOf(DispatcherType.class));
        handler.addServlet(DefaultServlet.class, "/");
        server.start();
    }
}
