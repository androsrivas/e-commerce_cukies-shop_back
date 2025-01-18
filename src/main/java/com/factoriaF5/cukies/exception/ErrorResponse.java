package com.factoriaF5.cukies.exception;

import lombok.*;

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

    public ErrorResponse(List<String> messages, int statusCode) {
        this.messages = messages;
        this.timestamp = LocalDateTime.now();
        this.statusCode = statusCode;
    }
}
