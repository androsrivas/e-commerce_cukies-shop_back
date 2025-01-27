package com.factoriaF5.cukies.exception.product;

import com.factoriaF5.cukies.exception.ApiException;
import org.springframework.http.HttpStatus;

public class InvalidPriceRangeException extends ApiException {
    public InvalidPriceRangeException(double minPrice, double maxPrice) {
        super(
                String.format(
                        "Invalid price range filter: minimum price (%.2f) cannot be greater than maximum price (%.2f)",
                        minPrice,
                        maxPrice
                ),
                HttpStatus.BAD_REQUEST
        );
    }
}
