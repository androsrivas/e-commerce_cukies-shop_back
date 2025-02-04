package com.factoriaF5.cukies.exception;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ApiException.class)
     public ResponseEntity<ErrorResponse> handleApiException(ApiException e) {
         ErrorResponse errorResponse = new ErrorResponse(e.getMessages(), e.getStatus());
        return new ResponseEntity<>(errorResponse, e.getStatus());
     }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException e) {
        List<String> errors = e.getBindingResult().getAllErrors()
                .stream()
                .map(error -> error.getDefaultMessage())
                .collect(Collectors.toList());

        return new ResponseEntity<>(new ErrorResponse(errors, HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception e) {
        ErrorResponse errorResponse = new ErrorResponse(
                List.of("An unexpected error occurred: " + e.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<ErrorResponse> handleDatabaseException(DataAccessException e) {
        ErrorResponse errorResponse = new ErrorResponse(
                List.of("Database error: " + e.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
