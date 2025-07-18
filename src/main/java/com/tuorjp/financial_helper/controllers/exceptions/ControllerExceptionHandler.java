package com.tuorjp.financial_helper.controllers.exceptions;

import com.tuorjp.financial_helper.exception.CategoryNotFoundException;
import com.tuorjp.financial_helper.exception.DuplicatedTupleException;
import com.tuorjp.financial_helper.exception.IncorrectEmailOrPasswordException;
import com.tuorjp.financial_helper.exception.InvalidCategoryTypeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {
  @ExceptionHandler(DuplicatedTupleException.class)
  public ResponseEntity<String> handleDuplicatedTupleException(DuplicatedTupleException e) {
    return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
  }

  @ExceptionHandler(CategoryNotFoundException.class)
  public ResponseEntity<String> handleCategoryNotFoundException(CategoryNotFoundException e) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
  }

  @ExceptionHandler(InvalidCategoryTypeException.class)
  public ResponseEntity<String> handleInvalidCategoryTypeException(InvalidCategoryTypeException e) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
  }

  @ExceptionHandler(IncorrectEmailOrPasswordException.class)
  public ResponseEntity<String> handleIncorrectEmailOrPasswordException(IncorrectEmailOrPasswordException e) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
  }
}
