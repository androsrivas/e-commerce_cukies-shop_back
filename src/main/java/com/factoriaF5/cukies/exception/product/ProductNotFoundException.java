package com.factoriaF5.cukies.exception.product;

import com.factoriaF5.cukies.exception.ApiException;
import org.springframework.http.HttpStatus;

public class ProductNotFoundException extends ApiException {
    private final String searchField;
    private final String searchValue;

    public ProductNotFoundException(String searchField, int searchValue) {
        super("Product with " + searchField + " " + searchValue + " not found.", HttpStatus.NOT_FOUND);
        this.searchField = searchField;
        this.searchValue = String.valueOf(searchValue);
    }

    public ProductNotFoundException(String searchField, String searchValue) {
        super("Product with " + searchField + " " + searchValue + " not found.", HttpStatus.NOT_FOUND);
        this.searchField = searchField;
        this.searchValue = searchValue;
    }
}
