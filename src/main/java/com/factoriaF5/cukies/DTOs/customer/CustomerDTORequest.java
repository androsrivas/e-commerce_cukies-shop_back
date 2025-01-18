package com.factoriaF5.cukies.DTOs.customer;

import jakarta.validation.constraints.*;

public record CustomerDTORequest(
        @NotBlank(message = "Username is mandatory.")
        @Size(min = 2, max = 20, message = "Username must be between 2 and 20 characters.")
        String username,

        @NotBlank(message = "Email is mandatory.")
        @Email(message = "Email must be valid.")
        String email,

        @NotBlank(message = "Password is mandatory.")
        @Size(min = 6, message = "Password must contain at least 6 characters.")
        String password
) {
}
