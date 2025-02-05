package com.factoriaF5.cukies.DTOs.product;

import com.factoriaF5.cukies.DTOs.image.ImageDTO;

import java.util.List;

public record ProductDTOResponse(
        int id,
        String name,
        double price,
        String description,
        boolean featured,
        ImageDTO image,
        String categoryName
) {
}
