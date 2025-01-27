package com.factoriaF5.cukies.DTOs.category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CategoryDTORequest(
        int id,
        @NotBlank(message = "Name is required")
        @Size(min = 1, max = 30, message = "Name must be at least 1 characters and max 30 characters.")
        String name

) {
}

