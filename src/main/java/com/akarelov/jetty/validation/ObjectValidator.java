package com.akarelov.jetty.validation;

import lombok.NoArgsConstructor;
import org.eclipse.jetty.http.HttpStatus;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@NoArgsConstructor
public class ObjectValidator {
    private final static String WRONG_NUMBER_FORMAT = "you passed not number format";

    public int parseInt(String number, HttpServletResponse resp) throws IOException {
        if (number == null) {
            resp.sendError(HttpStatus.BAD_REQUEST_400, WRONG_NUMBER_FORMAT);
        }
        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException ex) {
            resp.sendError(HttpStatus.BAD_REQUEST_400, WRONG_NUMBER_FORMAT);
        }
        return -1;
    }
}
