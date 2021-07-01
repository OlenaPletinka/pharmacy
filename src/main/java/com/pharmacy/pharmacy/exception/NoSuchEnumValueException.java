package com.pharmacy.pharmacy.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NoSuchEnumValueException extends ExceptionService {

  public NoSuchEnumValueException(String message) {
    super(message);
  }
}
