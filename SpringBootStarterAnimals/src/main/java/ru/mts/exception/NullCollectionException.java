package ru.mts.exception;

public class NullCollectionException extends Exception {
    public NullCollectionException() {
    }

    public NullCollectionException(String message) {
        super(message);
    }

    public NullCollectionException(String message, Throwable cause) {
        super(message, cause);
    }

    public NullCollectionException(Throwable cause) {
        super(cause);
    }

    public NullCollectionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
