package com.factoriaF5.cukies.exception.product;

import com.factoriaF5.cukies.exception.ApiException;
import org.springframework.http.HttpStatus;

public class ProductsNotFoundException extends ApiException {
    public ProductsNotFoundException() {
        super("No products found in the database.", HttpStatus.NOT_FOUND);
    }
}
