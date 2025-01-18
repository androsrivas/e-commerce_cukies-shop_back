package com.factoriaF5.cukies.exception;

public class CustomerExistsException extends RuntimeException {
  private final String email;

  public CustomerExistsException(String email) {
    super(email);
      this.email = email;
  }

  public String getEmail() {
    return email;
  }
}
