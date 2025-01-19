package com.factoriaF5.cukies.exception.customer;

import com.factoriaF5.cukies.exception.ApiException;
import org.springframework.http.HttpStatus;

public class CustomerExistsException extends ApiException {
  public CustomerExistsException(String email) {
    super("An account with " + email + " already exists.", HttpStatus.BAD_REQUEST);
  }
}
