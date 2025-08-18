package com.tuorjp.financial_helper.exception;

public class InvalidMonetaryValueException extends RuntimeException{
    public InvalidMonetaryValueException() {
        super("Monetary value cannot be null or below zero.");
    }
}
