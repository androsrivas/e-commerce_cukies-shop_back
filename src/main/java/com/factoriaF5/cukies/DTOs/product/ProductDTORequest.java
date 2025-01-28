package com.factoriaF5.cukies.DTOs.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

public record ProductDTORequest(
        int id,
        @NotBlank(message = "Name is required")
        @Size(min = 2, max = 50, message = "Name must be at least 2 characters and max 50 characters.")
        String name,
        @Positive
        double price,
        @Length(max = 500, message = "Description must not be more than 500 characters.")
        String description,
        @URL(message = "Invalid URL. Please provide a valid URL.")
        String imageUrl,
        boolean featured,
        Integer categoryId
) {
}
