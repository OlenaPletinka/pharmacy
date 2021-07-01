package com.pharmacy.pharmacy.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BasketIsEmptyException extends ExceptionService {

  public BasketIsEmptyException(String message) {
    super(message);
  }
}
