package ru.mts.exception;

public class ArgumentIsNotGreaterThanZeroException extends IllegalStateException {
    public ArgumentIsNotGreaterThanZeroException() {
    }

    public ArgumentIsNotGreaterThanZeroException(String s) {
        super(s);
    }

    public ArgumentIsNotGreaterThanZeroException(String message, Throwable cause) {
        super(message, cause);
    }

    public ArgumentIsNotGreaterThanZeroException(Throwable cause) {
        super(cause);
    }
}
