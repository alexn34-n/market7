package com.example.controller;

import com.example.component.Cart;
import com.example.dto.CartDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {

    private final Cart cart;

    @GetMapping
    public CartDto getCart(){
        return cart.getDto();
    }

    @PutMapping
    public  void updateCart(Long productId) {
        cart.addProduct(productId);
    }

    @DeleteMapping
    public void delete(){
        cart.clear();
    }
}
