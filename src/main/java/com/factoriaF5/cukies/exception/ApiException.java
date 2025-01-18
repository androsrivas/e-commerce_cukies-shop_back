package com.factoriaF5.cukies.exception;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
public abstract class ApiException extends RuntimeException {
    private final String message;
    private final HttpStatus status;

    protected ApiException(String message, HttpStatus status) {
        super(message);
        this.message = message;
        this.status = status;
    }

    public abstract List<String> getDetails();
}
