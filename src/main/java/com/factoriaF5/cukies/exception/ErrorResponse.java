package com.factoriaF5.cukies.exception;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ErrorResponse {
    private List<String> messages;
    private LocalDateTime timestamp = LocalDateTime.now();
    private int statusCode;
    private String status;

    public ErrorResponse(List<String> messages, HttpStatus status) {
        this.messages = messages;
        this.timestamp = LocalDateTime.now();
        this.statusCode = status.value();
        this.status = status.name();
    }

}
