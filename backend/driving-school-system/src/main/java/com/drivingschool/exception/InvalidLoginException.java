package com.drivingschool.exception;

public class InvalidLoginException extends RuntimeException {

    public InvalidLoginException() {
        super("Invalid email or password!");
    }

    public InvalidLoginException(String message) {
        super(message);
    }
}