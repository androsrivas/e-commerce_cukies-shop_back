package com.factoriaF5.cukies.DTOs.cart;

public record CartItemDTO(
        int id,
        int productId,
        String productName,
        int quantity,
        double price
) {
}
