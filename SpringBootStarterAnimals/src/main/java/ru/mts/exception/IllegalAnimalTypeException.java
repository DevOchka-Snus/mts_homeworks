package ru.mts.exception;

public class IllegalAnimalTypeException extends IllegalArgumentException {
    public IllegalAnimalTypeException() {
    }

    public IllegalAnimalTypeException(String s) {
        super(s);
    }

    public IllegalAnimalTypeException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalAnimalTypeException(Throwable cause) {
        super(cause);
    }
}
