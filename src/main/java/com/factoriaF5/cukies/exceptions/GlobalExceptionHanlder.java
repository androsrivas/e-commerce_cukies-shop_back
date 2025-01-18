package com.factoriaF5.cukies.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHanlder {
     public ResponseEntity<Object> handleInvalidArgument(MethodArgumentNotValidException e) {
         List<String> errors = new ArrayList<>();
         e.getBindingResult().getAllErrors().forEach(
                 (error) -> errors.add(error.getDefaultMessage()));
         return new ResponseEntity<>(new ErrorResponse(errors), HttpStatus.BAD_REQUEST);
     }

}
