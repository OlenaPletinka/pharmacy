package com.pharmacy.pharmacy.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidDataForAddindToWharehouseException extends ExceptionService {

  public InvalidDataForAddindToWharehouseException(String message) {
    super(message);
  }
}
