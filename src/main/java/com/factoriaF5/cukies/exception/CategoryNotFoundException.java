package com.factoriaF5.cukies.exception;

import com.factoriaF5.cukies.DTOs.category.CategoryDTO;

public class CategoryNotFoundException extends RuntimeException {
    public CategoryNotFoundException(int id) {
        super("Category with id " + id + " not found");
    }
}
