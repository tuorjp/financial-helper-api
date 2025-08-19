package com.tuorjp.financial_helper.controllers.exceptions;

import com.tuorjp.financial_helper.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

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

  @ExceptionHandler(InvalidDateArgumentException.class)
  public ResponseEntity<String> handleInvalidDateArgument(InvalidDateArgumentException e) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
  }

  @ExceptionHandler(InvalidMonetaryValueException.class)
  public ResponseEntity<String> handleInvalidMonetaryValueException(InvalidMonetaryValueException e) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
  }

  @ExceptionHandler(NoSuchElementException.class)
  public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException e) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Element not found");
  }

  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Illegal argument");
  }
}
