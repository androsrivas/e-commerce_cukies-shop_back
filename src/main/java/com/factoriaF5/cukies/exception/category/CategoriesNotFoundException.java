package com.factoriaF5.cukies.exception.category;

import com.factoriaF5.cukies.exception.ApiException;
import org.springframework.http.HttpStatus;

public class CategoriesNotFoundException extends ApiException {
    public CategoriesNotFoundException() {
        super("No categories found in the database.", HttpStatus.NOT_FOUND);
    }
}
