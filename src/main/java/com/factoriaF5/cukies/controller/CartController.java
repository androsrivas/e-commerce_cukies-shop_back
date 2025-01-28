package com.factoriaF5.cukies.controller;

import com.factoriaF5.cukies.DTOs.cart.CartDTOResponse;
import com.factoriaF5.cukies.exception.product.ProductNotFoundException;
import com.factoriaF5.cukies.model.Customer;
import com.factoriaF5.cukies.service.CartService;
import com.factoriaF5.cukies.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {
    private final CartService cartService;
    private final CustomerService customerService;

    public CartController(CartService cartService, CustomerService customerService) {
        this.cartService = cartService;
        this.customerService = customerService;
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<CartDTOResponse> getCart(@PathVariable int customerId) {
        CartDTOResponse cartDTO = cartService.getCartByCustomer(customerService.findById(customerId));
        return new ResponseEntity<>(cartDTO, HttpStatus.OK);
    }

    @PostMapping("/{customerId}/add/{productId}")
    public ResponseEntity<CartDTOResponse> addProductToCart(
            @PathVariable int customerId,
            @PathVariable int productId
    ) {
        try {
            CartDTOResponse cartDTO = cartService.addProductToCart(customerService.findById(customerId), productId);
            return new  ResponseEntity<>(cartDTO, HttpStatus.OK);
        } catch (ProductNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new CartDTOResponse(
                            customerId,
                            customerService.findById(customerId).getUsername(),
                            List.of(),
                            0.0,
                            LocalDateTime.now(),
                            LocalDateTime.now())
                    );
        }
    }

    @DeleteMapping("/{customerId}/remove/{productId}")
    public ResponseEntity<CartDTOResponse> removeProductFromCart(
            @PathVariable int customerId,
            @PathVariable int productId
    ) {
        Customer customer = customerService.findById(customerId);
        CartDTOResponse cartDTO = cartService.removeProductFromCart(customer, productId);
        return new ResponseEntity<>(cartDTO, HttpStatus.OK);
    }

    @DeleteMapping("{customerId}/clear")
    public ResponseEntity<CartDTOResponse> clearCart (
            @PathVariable int customerId
    ) {
        Customer customer = customerService.findById(customerId);
        CartDTOResponse cartDTO = cartService.clearCart(customer);
        return new ResponseEntity<>(cartDTO, HttpStatus.OK);
    }
}
