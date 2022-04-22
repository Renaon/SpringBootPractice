package com.example.demo.controller;

import com.example.demo.entity.Category;
import com.example.demo.entity.Product;
import com.example.demo.service.CartService;
import com.example.demo.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
public class ProductController {
    private final ProductService productService = new ProductService();
    private final CartService cartService = new CartService();

    @GetMapping("/")
//    @ResponseBody
    public String getIndex(Model model){
        List<Product> products = productService.getAll();
        model.addAttribute("products", products);
        return "index";
     }

    @GetMapping("/add")
    public String add(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        return "form";
    }

    @RequestMapping("/success")
    public String pcessAddress(@ModelAttribute("form") Product product){
        productService.addProduct(product);
        return "success";
    }

    @GetMapping("/store")
    public String getAll(Model model){
        List<Product> products = productService.getAll();
        model.addAttribute("products", products);
        return "store";
    }

    @GetMapping("/categories")
    public String getCategories(Model model){
        List<Category> categories = productService.getCategories();
        model.addAttribute("categories", categories);
        return "categories";
    }

    @GetMapping("productdel")
    public String productdel(Model model, @RequestParam String id){
        productService.dropProduct(Integer.parseInt(id));
        return getIndex(model);
    }

    @GetMapping("/category")
    public String getCategoryProductString(Model model, @RequestParam String category){
        List<Product> product = productService.getProductsByCategory(category);
        model.addAttribute("products", product);
        return "category";
    }


    //дописать 2 метода для отображения корзины и для добавления в корзину
    @GetMapping("/buy")
    public String addProductToCart(Model model, @RequestParam String productTitle){

        return "index";
    }

}
