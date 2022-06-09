package com.example.demo.controller;

import com.example.demo.entity.OrderItem;
import com.example.demo.entity.Product;
import com.example.demo.entity.ShopCart;
import com.example.demo.service.CartService;
import com.example.demo.utils.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CartController {
    @Autowired
    private CartService cartService;

    @GetMapping("/cart")
    public String getUserCart(Model model, HttpSession session){
        System.out.println("ДОШОЛ");
        ShoppingCart cart = cartService.getCart(session);
        List<OrderItem> items = cart.getItems();
        List<Product> products = new ArrayList<>();
        for (OrderItem orderItem : items){
            products.add(orderItem.getProduct());
        }
        model.addAttribute("products", products);
        model.addAttribute("cart", cart);
        return "cart";
    }
}
