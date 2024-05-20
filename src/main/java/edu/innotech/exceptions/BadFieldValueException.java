package edu.innotech.exceptions;

public class BadFieldValueException extends RuntimeException{
    public BadFieldValueException(String message) {
        super(message);
    }
}