package com.factoriaF5.cukies.repository;

import com.factoriaF5.cukies.model.Cart;
import com.factoriaF5.cukies.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Integer> {
    Optional<Cart> findByCustomer(Customer customer);
}
