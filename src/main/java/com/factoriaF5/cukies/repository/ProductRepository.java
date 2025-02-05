package com.factoriaF5.cukies.repository;

import com.factoriaF5.cukies.model.Category;
import com.factoriaF5.cukies.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findByCategoryNameIgnoreCaseAndPriceBetween(String categoryName, Double minPrice, Double maxPrice);
    List<Product> findByCategory(Category category);
}
