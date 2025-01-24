package com.factoriaF5.cukies.repository;

import com.factoriaF5.cukies.model.Category;
import com.factoriaF5.cukies.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findByCategory(Optional<Category> category);
    List<Product> findByPriceBetween(double minPrice, double maxPrice);
}
