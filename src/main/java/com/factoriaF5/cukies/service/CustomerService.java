package com.factoriaF5.cukies.service;

import com.factoriaF5.cukies.DTOs.customer.CustomerDTORequest;
import com.factoriaF5.cukies.DTOs.customer.CustomerDTOResponse;
import com.factoriaF5.cukies.DTOs.customer.CustomerMapper;
import com.factoriaF5.cukies.exception.CustomerExistsException;
import com.factoriaF5.cukies.model.Customer;
import com.factoriaF5.cukies.repository.CustomerRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerService(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Transactional
    public CustomerDTOResponse saveCustomer(CustomerDTORequest customerRequest) {
        Customer newCustomer = customerMapper.toEntity(customerRequest);

        if (customerRepository.existsByEmail(newCustomer.getEmail())){
            throw new CustomerExistsException(customerRequest.email());
        }

        Customer savedCustomer = customerRepository.save(newCustomer);
        return customerMapper.toDTOResponse(savedCustomer);
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Optional<Customer> findCustomerById(int id) {
        Optional<Customer> foundCustomer = customerRepository.findById(id);
        if (foundCustomer.isPresent()) {
            return foundCustomer;
        }
        throw new RuntimeException();
    }

    public Optional<Customer> findCustomerByUsername(String username) {
        Optional<Customer> foundCustomer = customerRepository.findByUsername(username);
        if (foundCustomer.isPresent()) {
            return foundCustomer;
        }
        throw new RuntimeException();
    }

    public Optional<Customer> findCustomerByEmail(String email) {
        Optional<Customer> foundCustomer = customerRepository.findByEmail(email);
        if (foundCustomer.isPresent()) {
            return foundCustomer;
        }
        throw new RuntimeException();
    }

    public Customer updateCustomer(int id, Customer customerDetails) {
        Optional<Customer> foundCustomer = customerRepository.findById(customerDetails.getId());
        if (foundCustomer.isPresent()) {
            Customer existingCustomer = foundCustomer.get();
            existingCustomer.setUsername(customerDetails.getUsername());
            existingCustomer.setEmail(customerDetails.getEmail());
            existingCustomer.setPassword(customerDetails.getPassword());

            return customerRepository.save(existingCustomer);
        }
        throw new RuntimeException();
    }

    public void deleteCustomer(int id) {
        customerRepository.deleteById(id);
    }

}
