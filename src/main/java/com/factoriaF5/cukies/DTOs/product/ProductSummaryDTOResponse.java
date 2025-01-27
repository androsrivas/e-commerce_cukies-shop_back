package com.factoriaF5.cukies.DTOs.product;

public record ProductSummaryDTOResponse(
        String name,
        double price,
        String imageUrl,
        boolean featured,
        String categoryName
) {
}
