package com.akarelov.jetty.exception;

public class NoteNotExistsException extends RuntimeException {
    public NoteNotExistsException() {
        super();
    }

    public NoteNotExistsException(String s) {
        super(s);
    }

    public NoteNotExistsException(String s, Throwable throwable) {
        super(s, throwable);
    }
}
