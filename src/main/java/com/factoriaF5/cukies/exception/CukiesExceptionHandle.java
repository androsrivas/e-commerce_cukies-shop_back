package com.factoriaF5.cukies.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class CukiesExceptionHandle {

   @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException exception){
       BindingResult bindingResult = exception.getBindingResult();
       List<FieldError> fieldErrors = bindingResult.getFieldErrors();

       List<String> errorMessages = fieldErrors.stream()
               .map(fieldError -> fieldError.getDefaultMessage()).toList();

       ErrorResponse errorResponse = new ErrorResponse(
               "Validation failed",
               errorMessages,
               fieldErrors.size()
       );
       return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
   }

}
