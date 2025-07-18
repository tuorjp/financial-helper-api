package com.tuorjp.financial_helper.exception;

public class CategoryNotFoundException extends RuntimeException {
  public CategoryNotFoundException(String message) {
    super(message);
  }

  public CategoryNotFoundException(Integer id) {
    super("Category not found with ID: " + id);
  }
}
