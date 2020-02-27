package com.akarelov.jetty.configuration;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.servlet.ErrorPageErrorHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ErrorHandler extends ErrorPageErrorHandler {
    @Override
    public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String message = response.getHeader("message");
        String errorCode = response.getHeader("errorCode");
        response.getWriter()
                .append("{\"message\":\"" + message + "\",")
                .append("\"code\":\"" + errorCode)
                .append("\"}");
    }
}
