package com.factoriaF5.cukies.DTOs.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.URL;

public record ProductSummaryDTORequest(
        @NotBlank(message = "Name is required")
        @Size(min = 2, max = 50, message = "Name must be at least 2 characters and max 50 characters.")
        String name,
        @Positive
        double price,
        @URL(message = "Invalid URL. Please provide a valid URL")
        String imageUrl,
        boolean featured,
        int categoryId
) {

}