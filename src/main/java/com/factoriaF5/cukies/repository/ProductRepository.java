package com.factoriaF5.cukies.repository;

import com.factoriaF5.cukies.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
