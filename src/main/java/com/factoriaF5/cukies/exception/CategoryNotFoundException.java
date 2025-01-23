package com.factoriaF5.cukies.exception;

public class CategoryNotFoundException extends RuntimeException {
    public CategoryNotFoundException(int id) {
        super("Category with id " + id + " not found");
    }
}
