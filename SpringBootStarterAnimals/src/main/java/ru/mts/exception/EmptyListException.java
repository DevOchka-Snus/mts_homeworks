package ru.mts.exception;

public class EmptyListException extends IllegalArgumentException {
    public EmptyListException() {
    }

    public EmptyListException(String s) {
        super(s);
    }

    public EmptyListException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmptyListException(Throwable cause) {
        super(cause);
    }
}
