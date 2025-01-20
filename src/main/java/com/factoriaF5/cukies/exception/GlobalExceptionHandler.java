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

    //maneja excepciones específicas que extienden de ApiException
    @ExceptionHandler(ApiException.class)
     public ResponseEntity<Object> handleInvalidArgument(ApiException e) {
         ErrorResponse errorResponse = new ErrorResponse(e.getMessages(), e.getStatus());
        return new ResponseEntity<>(errorResponse, e.getStatus());
     }

     //maneja excepciones de validación
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException e) {
        List<String> errors = e.getBindingResult().getAllErrors()
                .stream()
                .map(error -> error.getDefaultMessage())
                .collect(Collectors.toList());

        return new ResponseEntity<>(new ErrorResponse(errors, HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
    }

    //maneja excepciones genéricas
    public ResponseEntity<ErrorResponse> handleGenericException(Exception e) {
        ErrorResponse errorResponse = new ErrorResponse(
                List.of("An unexpected error occurred: " + e.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //maneja excepciones si hay problemas con la conexión de la base de datos
    public ResponseEntity<ErrorResponse> handleDatabaseException(DataAccessException e) {
        ErrorResponse errorResponse = new ErrorResponse(
                List.of("Database error: " + e.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
