package com.factoriaF5.cukies.DTOs.cart;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.List;

public record CartDTOResponse(
        int customerId,
        String customerName,
        List<CartItemDTO> items,
        double totalPrice,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yy HH:mm:ss")
        LocalDateTime createdAt,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yy HH:mm:ss")
        LocalDateTime updatedAt
) {
}
