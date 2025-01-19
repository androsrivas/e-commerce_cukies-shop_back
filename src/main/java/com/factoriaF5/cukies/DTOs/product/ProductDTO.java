package com.factoriaF5.cukies.DTOs.product;

import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Currency;
import org.hibernate.validator.constraints.URL;

public record ProductDTO(
        @NotBlank(message = "Name is required")
        @Size(min = 1, max = 50, message = "Name must be at least 2 characters and max 50 characters.")
        String name,
        @Positive
        @Currency("EUR")
        double price,
        @URL(
                protocol = "https",
                host = "example.com",
                message = "Invalid URL. Please provide a valid URL")
        String imageUrl,
        boolean featured

) {
}
