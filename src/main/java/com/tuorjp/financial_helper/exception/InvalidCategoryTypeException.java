package com.tuorjp.financial_helper.exception;

public class InvalidCategoryTypeException extends RuntimeException {
  public InvalidCategoryTypeException(String message) {
    super(message);
  }

  public InvalidCategoryTypeException(Integer type) {
    super("Invalid category type " + type);
  }
}
