package com.factoriaF5.cukies.DTOs.product;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record ProductDTO(
        @NotEmpty(message = "Name is required")
        @NotNull
        @Size(min = 1, max = 50, message = "Name must be at least 2 characters and max 50 characters.")
        String name,
        @Positive
        double price,
        String imageUrl,
        boolean featured

) {
}
