package com.factoriaF5.cukies.DTOs.cart;


import java.util.List;

public record CartDTOResponse(
        int customerId,
        List<CartItemDTO> items,
        double totalPrice
) {
}
