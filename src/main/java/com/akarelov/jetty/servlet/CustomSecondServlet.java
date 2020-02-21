package com.akarelov.jetty.servlet;

import com.akarelov.jetty.service.Service;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import org.eclipse.jetty.http.HttpStatus;
import org.slf4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Singleton
public class CustomSecondServlet extends HttpServlet {
    private final Service service;
    private final Logger logger;

    @Inject
    public CustomSecondServlet(Service service, @Named("secondLogger") Logger logger) {
        this.service = service;
        this.logger = logger;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setStatus(HttpStatus.OK_200);
        String str = service.print() + " " +req.getMethod();

        logger.info(str);

        resp.getWriter().println(str);
    }
}
