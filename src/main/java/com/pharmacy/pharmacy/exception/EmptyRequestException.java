package com.pharmacy.pharmacy.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EmptyRequestException extends ExceptionService {
  public EmptyRequestException(String message) {
    super(message);
  }
}
