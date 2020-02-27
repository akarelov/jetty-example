package com.akarelov.jetty.exception;

public class UserNotExistsException extends RuntimeException {
    public UserNotExistsException() {
        super();
    }

    public UserNotExistsException(String s) {
        super(s);
    }
}
