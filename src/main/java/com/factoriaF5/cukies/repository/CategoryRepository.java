package com.factoriaF5.cukies.repository;

import com.factoriaF5.cukies.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
