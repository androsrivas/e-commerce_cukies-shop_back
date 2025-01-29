package com.factoriaF5.cukies.repository;

import com.factoriaF5.cukies.model.Category;
import com.factoriaF5.cukies.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findByCategoryNameAndPriceBetween(String categoryName, Double minPrice, Double maxPrice);
    List<Product> findByPriceBetween(Double minPrice, Double maxPrice);
    List<Product> findByCategoryName(String categoryName);

    List<Product> findByCategory(Category category);
}
