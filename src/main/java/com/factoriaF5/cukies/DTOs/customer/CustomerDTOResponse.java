package com.factoriaF5.cukies.DTOs.customer;

import com.factoriaF5.cukies.DTOs.product.ProductDTOResponse;

import java.util.List;

public record CustomerDTOResponse(
        String username,
        String email,
        List<ProductDTOResponse> items
) {
}
