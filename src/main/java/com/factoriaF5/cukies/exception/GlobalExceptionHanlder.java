package com.factoriaF5.cukies.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHanlder {

    //maneja excepciones específicas
    @ExceptionHandler(ApiException.class)
     public ResponseEntity<Object> handleInvalidArgument(ApiException e) {
         List<String> errors = e.getDetails();
         ErrorResponse errorResponse = new ErrorResponse(errors, e.getStatus().value());
        return new ResponseEntity<>(errorResponse, e.getStatus());
     }

     //maneja excepciones de validación
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException e) {
        List<String> errors = new ArrayList<>();
        e.getBindingResult().getAllErrors().forEach(
                error -> errors.add(error.getDefaultMessage()));
        return new ResponseEntity<>(new ErrorResponse(errors, HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST);
    }

    //maneja excepciones genéricas
    public ResponseEntity<ErrorResponse> handleGenericException(Exception e) {
        List<String> errors = new ArrayList<>();
        errors.add("An unexpected error occurred: " + e.getMessage());
        return new ResponseEntity<>(new ErrorResponse(errors, HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
