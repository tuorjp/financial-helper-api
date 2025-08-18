package com.tuorjp.financial_helper.exception;

public class InvalidDateArgumentException extends RuntimeException{
    public InvalidDateArgumentException(String message) {
        super(message);
    }

    public InvalidDateArgumentException() {
        super("Date argument cannot be null or must be a date format value.");
    }
}
