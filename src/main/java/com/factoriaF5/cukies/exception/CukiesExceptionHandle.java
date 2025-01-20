package com.factoriaF5.cukies.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CukiesExceptionHandle {
   @ExceptionHandler(ObjectNotFoundException.class)
   public ResponseEntity<Object> handleProductNotFoundException(ObjectNotFoundException exception){
       ErrorResponse errorResponse = new ErrorResponse(exception.getMessage());
       return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
   }
}
