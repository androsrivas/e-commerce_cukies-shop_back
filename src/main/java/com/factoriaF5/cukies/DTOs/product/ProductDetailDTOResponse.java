package com.factoriaF5.cukies.DTOs.product;

public record ProductDetailDTOResponse(
        String name,
        double price,
        String description,
        String imageUrl,
        boolean featured,
        String categoryName
) {
}
