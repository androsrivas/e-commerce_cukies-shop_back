package com.factoriaF5.cukies.exception.customer;

import com.factoriaF5.cukies.exception.ApiException;
import org.springframework.http.HttpStatus;

public class CustomersNotFoundException extends ApiException {
    public CustomersNotFoundException() {
        super("No customers found in the database.", HttpStatus.NOT_FOUND);
    }
}
