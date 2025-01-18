package com.factoriaF5.cukies.exceptions;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ErrorResponse {
    private List<String> messages;
    private LocalDateTime timestamp;

    public ErrorResponse(List<String> messages) {
        this.messages = messages;
        this.timestamp = LocalDateTime.now();
    }
}
