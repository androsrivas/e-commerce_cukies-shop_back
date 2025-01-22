package com.factoriaF5.cukies.DTOs.product;

import com.factoriaF5.cukies.DTOs.category.CategoryDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.URL;

import java.util.List;

public record ProductDTO(
        @NotBlank(message = "Name is required")
        @Size(min = 1, max = 50, message = "Name must be at least 2 characters and max 50 characters.")
        String name,
        @Positive
        double price,
        @URL(message = "Invalid URL. Please provide a valid URL")
        String imageUrl,
        boolean featured,
        CategoryDTO category,
        List<com.factoriaF5.cukies.model.Customer> customers

) {

}