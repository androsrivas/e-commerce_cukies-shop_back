package com.factoriaF5.cukies.service;

import com.factoriaF5.cukies.DTOs.cart.CartDTORequest;
import com.factoriaF5.cukies.DTOs.cart.CartDTOResponse;
import com.factoriaF5.cukies.DTOs.cart.CartMapper;
import com.factoriaF5.cukies.exception.product.ProductNotFoundException;
import com.factoriaF5.cukies.model.Cart;
import com.factoriaF5.cukies.model.CartItem;
import com.factoriaF5.cukies.model.Customer;
import com.factoriaF5.cukies.model.Product;
import com.factoriaF5.cukies.repository.CartRepository;
import com.factoriaF5.cukies.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class CartService {
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private CartMapper cartMapper;

    public CartService(CartRepository cartRepository, ProductRepository productRepository, CartMapper cartMapper) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.cartMapper = cartMapper;
    }

    private Cart findOrCreateCart(Customer customer) {
        return cartRepository.findByCustomer(customer)
                .orElseGet(() -> {
                    Cart newCart = new Cart();
                    newCart.setCustomer(customer);
                    return cartRepository.save(newCart);
                });
    }

    public CartDTOResponse getCartByCustomer(Customer customer) {
        Cart cart = findOrCreateCart(customer);
        double totalPrice = calculateTotal(cart);
        return new CartDTOResponse(customer.getId(), cartMapper.toDTOResponse(cart).items(), totalPrice);
    }

    public CartDTOResponse addProductToCart(Customer customer, int productId) {
        Cart cart = findOrCreateCart(customer);
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("ID", productId));
        CartItem existingItem = cart.getItems().stream()
                .filter(item -> item.getProduct().getId() == productId)
                .findFirst()
                .orElse(null);

        if (existingItem != null) {
            existingItem.setQuantity(existingItem.getQuantity() + 1);
        } else {
            CartItem newItem = new CartItem(cart, product, 1);
            cart.getItems().add(newItem);
        }

        double totalPrice = calculateTotal(cart);
        cartRepository.save(cart);
        return new CartDTOResponse(cart.getCustomer().getId(), cartMapper.toDTOResponse(cart).items(), totalPrice);
    }

    private double calculateTotal(Cart cart) {
        return cart.getItems().stream()
                .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
                .sum();
    }
}
