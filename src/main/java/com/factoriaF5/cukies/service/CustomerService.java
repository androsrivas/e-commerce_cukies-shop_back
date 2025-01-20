package com.factoriaF5.cukies.service;

import com.factoriaF5.cukies.DTOs.customer.CustomerDTORequest;
import com.factoriaF5.cukies.DTOs.customer.CustomerDTOResponse;
import com.factoriaF5.cukies.DTOs.customer.CustomerMapper;
import com.factoriaF5.cukies.exception.customer.CustomerExistsException;
import com.factoriaF5.cukies.exception.customer.CustomerNotFoundException;
import com.factoriaF5.cukies.exception.customer.CustomersNotFoundException;
import com.factoriaF5.cukies.model.Customer;
import com.factoriaF5.cukies.repository.CustomerRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerService(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    public CustomerDTOResponse saveCustomer(CustomerDTORequest customerRequest) {
        if (customerRepository.existsByEmail(customerRequest.email())) {
            throw new CustomerExistsException(customerRequest.email());
        }

        Customer newCustomer = customerMapper.toEntity(customerRequest);
        Customer savedCustomer = customerRepository.save(newCustomer);

        return customerMapper.toDTOResponse(savedCustomer);
    }

    @Transactional(readOnly = true)
    public List<CustomerDTOResponse> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();

        if (customers.isEmpty()) throw new CustomersNotFoundException();

        return customers.stream()
                .map(customer -> customerMapper.toDTOResponse(customer))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CustomerDTOResponse findCustomerById(int id) {
       return customerRepository.findById(id)
               .map(customer -> customerMapper.toDTOResponse(customer))
               .orElseThrow(() -> new CustomerNotFoundException("ID", id));
    }

    @Transactional(readOnly = true)
    public CustomerDTOResponse findCustomerByUsername(String username) {
        return customerRepository.findByUsername(username)
                .map(customer -> customerMapper.toDTOResponse(customer))
                .orElseThrow(() -> new CustomerNotFoundException("username", username));
    }

    @Transactional(readOnly = true)
    public CustomerDTOResponse findCustomerByEmail(String email) {
        return customerRepository.findByEmail(email)
                .map(customer -> customerMapper.toDTOResponse(customer))
                .orElseThrow(() -> new CustomerNotFoundException("email", email));
    }

    public CustomerDTOResponse updateCustomer(int id, CustomerDTORequest customerDetails) {
        Customer existingCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("ID", id));

        existingCustomer.setUsername(customerDetails.username());
        existingCustomer.setEmail(customerDetails.email());
        existingCustomer.setPassword(customerDetails.password());

        Customer updatedCustomer = customerRepository.save(existingCustomer);
        return customerMapper.toDTOResponse(updatedCustomer);
    }

    public String deleteCustomer(int id) {
        Optional<Customer> foundCustomer = customerRepository.findById(id);
        if (foundCustomer.isEmpty()) throw new CustomerNotFoundException("ID", id);
        customerRepository.deleteById(id);
        return "Customer with ID " + id + " deleted successfully.";
    }

}
