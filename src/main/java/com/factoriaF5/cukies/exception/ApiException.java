package com.factoriaF5.cukies.exception;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.List;

@Getter
@AllArgsConstructor
public abstract class ApiException extends RuntimeException {
    private final HttpStatus status;

    protected ApiException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public List<String> getMessages() {
        return Collections.singletonList(getMessage());
    }
}
