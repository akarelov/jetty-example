package com.akarelov.jetty.configuration;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Response;
import org.eclipse.jetty.servlet.ErrorPageErrorHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.eclipse.jetty.http.MimeTypes.Type.APPLICATION_JSON;

public class ErrorHandler extends ErrorPageErrorHandler {
    @Override
    public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String message = ((Response) response).getReason();
        int errorCode = response.getStatus();
        response.setContentType(APPLICATION_JSON.asString());
        response.getWriter()
                .append("{\"message\":\"").append(message).append("\",")
                .append("\"errorCode\":\"").append(String.valueOf(errorCode))
                .append("\"}");
    }
}
