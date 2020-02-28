package com.akarelov.jetty.configuration;

import org.eclipse.jetty.http.HttpStatus;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.servlet.ErrorPageErrorHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.eclipse.jetty.http.MimeTypes.Type.APPLICATION_JSON;

public class ErrorHandler extends ErrorPageErrorHandler {
    @Override
    public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Exception exception = (Exception) request.getAttribute("javax.servlet.error.exception");

        response.setContentType(APPLICATION_JSON.asString());
        response.setStatus(HttpStatus.BAD_REQUEST_400);
        response.getWriter()
                .append("{\"message\":\"").append(exception.getMessage()).append("\",")
                .append("\"errorCode\":\"").append(String.valueOf(HttpStatus.BAD_REQUEST_400))
                .append("\"}");
    }
}
