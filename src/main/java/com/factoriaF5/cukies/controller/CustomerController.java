package com.factoriaF5.cukies.controller;

import com.factoriaF5.cukies.DTOs.customer.*;
import com.factoriaF5.cukies.exception.customer.CustomerNotFoundException;
import com.factoriaF5.cukies.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public ResponseEntity<List<CustomerDTOResponse>> getAllCustomers() {
        return new ResponseEntity<>(customerService.getAllCustomers(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTOResponse> getCustomerById(@PathVariable Integer id) {
        return new ResponseEntity<>(customerService.findCustomerById(id), HttpStatus.OK);
    }

    @GetMapping("/search/username/{username}")
    public ResponseEntity<CustomerDTOResponse> getCustomerByUsername(@PathVariable String username) {
        return new ResponseEntity<>(customerService.findCustomerByUsername(username), HttpStatus.OK);
    }

    @GetMapping("/search/email/{email}")
    public ResponseEntity<CustomerDTOResponse> getCustomerByEmail(@PathVariable String email) {
        return new ResponseEntity<>(customerService.findCustomerByEmail(email), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDTOResponse> updateCustomer(
            @PathVariable Integer id,
            @Valid @RequestBody CustomerDTORequest customerDetails) {
        return new ResponseEntity<>(customerService.updateCustomer(id, customerDetails), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable Integer id) {
        String message = customerService.deleteCustomer(id);
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }

}
