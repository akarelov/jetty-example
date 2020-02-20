package com.akarelov.jetty;

import com.akarelov.jetty.servlet.CustomServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;

public class Application {
    public static void main(String[] args) throws Exception {
        Server server = new Server(7070);
        ServletContextHandler handler = new ServletContextHandler(server, "/");
        handler.addServlet(CustomServlet.class, "/servlet/number/1");
        server.start();
    }
}
