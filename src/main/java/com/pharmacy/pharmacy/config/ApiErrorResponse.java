package com.pharmacy.pharmacy.config;

import lombok.Data;

@Data
public class ApiErrorResponse {
  private String message;

  public ApiErrorResponse(String message) {
    this.message = message;
  }

}
