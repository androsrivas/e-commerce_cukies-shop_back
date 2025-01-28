package com.factoriaF5.cukies.DTOs.cart;

import java.util.List;

public record CartDTORequest(
        int id,
        int customerId,
        List<CartItemDTO> items,
        double totalPrice
) {
}
