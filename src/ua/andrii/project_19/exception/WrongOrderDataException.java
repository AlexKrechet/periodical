package ua.andrii.project_19.exception;

public class WrongOrderDataException extends Exception {
    public WrongOrderDataException() {
        super();
    }

    public WrongOrderDataException(String message) {
        super(message);
    }
}
