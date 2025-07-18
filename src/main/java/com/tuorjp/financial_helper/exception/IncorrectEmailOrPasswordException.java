package com.tuorjp.financial_helper.exception;

import org.springframework.security.core.AuthenticationException;

public class IncorrectEmailOrPasswordException extends AuthenticationException {
  public IncorrectEmailOrPasswordException(String message) {
    super(message);
  }
}
