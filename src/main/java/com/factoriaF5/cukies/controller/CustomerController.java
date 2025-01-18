package com.factoriaF5.cukies.controller;

import com.factoriaF5.cukies.DTOs.customer.*;
import com.factoriaF5.cukies.exception.CustomerExistsException;
import com.factoriaF5.cukies.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/customers")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<CustomerDTOResponse> createCustomer(@Valid @RequestBody CustomerDTORequest customerDTORequest) {
        return new ResponseEntity<>(customerService.saveCustomer(customerDTORequest), HttpStatus.CREATED);
    }

//    @ExceptionHandler(CustomerExistsException.class)
//    public ResponseEntity<String> handleCustomerExists(CustomerExistsException e) {
//        return ResponseEntity.status(HttpStatus.CONFLICT).body("Este email ya está registrado: " + e.getEmail());
//    }
}
