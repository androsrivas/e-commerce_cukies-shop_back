package com.factoriaF5.cukies.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Setter
@Getter
@NoArgsConstructor

public class ErrorResponse {
    private String message;
    private LocalDateTime timestamp;
    private Object data;
    private int errorCount;

    public ErrorResponse(String message, Object data, int errorCount) {
        this.message = message;
        this.timestamp = LocalDateTime.now();
        this.data = data;
        this.errorCount = errorCount;
    }
    public String getTimestamp(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy  HH:mm:ss");
        return timestamp.format(formatter);
    }

    public String getMessage() {
        return message;
    }
}
