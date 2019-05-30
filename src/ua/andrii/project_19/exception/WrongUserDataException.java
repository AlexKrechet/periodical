package ua.andrii.project_19.exception;

public class WrongUserDataException extends Exception {
    public WrongUserDataException() {
        super();
    }

    public WrongUserDataException(String message) {
        super(message);
    }
}
