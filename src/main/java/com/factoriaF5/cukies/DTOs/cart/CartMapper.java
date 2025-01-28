package com.factoriaF5.cukies.DTOs.cart;

import com.factoriaF5.cukies.model.Cart;
import com.factoriaF5.cukies.model.CartItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CartMapper {
    @Mapping(target = "customerId", source = "cart.customer.id")
    @Mapping(target = "customerName", source = "cart.customer.username")
    @Mapping(target = "createdAt", source = "cart.createdAt")
    @Mapping(target = "updatedAt", source = "cart.updatedAt")
    CartDTOResponse toDTOResponse(Cart cart);

    @Mapping(target = "productId", source = "cart.customer.id")
    @Mapping(target = "productName", source = "cartItem.product.name")
    @Mapping(target = "price", expression = "java(cartItem.getProduct().getPrice() * cartItem.getQuantity())")
    CartItemDTO toDTOResponse(CartItem cartItem);
}
