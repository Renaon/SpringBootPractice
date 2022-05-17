package com.example.demo.controller;

import com.example.demo.service.CartService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class CartController {
    private final CartService cartService = new CartService();

    @GetMapping("/cart")
    public String getUserCart(Model model, Principal principal){

        return "cart";
    }
}
