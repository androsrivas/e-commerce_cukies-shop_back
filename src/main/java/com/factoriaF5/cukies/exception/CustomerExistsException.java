package com.factoriaF5.cukies.exception;

public class CustomerExistsException extends RuntimeException {
  private final String email;

  public CustomerExistsException(String email) {
    super("An account with " + email + " already exists.");
      this.email = email;
  }

  public String getEmail() {
    return email;
  }
}
