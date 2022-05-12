package com.example.demo.controller;

import com.example.demo.entity.Category;
import com.example.demo.entity.Product;
import com.example.demo.entity.ShopCart;
import com.example.demo.service.CartService;
import com.example.demo.service.ProductService;
import com.example.demo.service.UserService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.Principal;
import java.util.List;

@RestController
public class ProductController {
    private final ProductService productService = new ProductService();
    private final CartService cartService = new CartService();
    private final UserService userService = new UserService();

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
    public String pcessAddress(@ModelAttribute("form") Product product,
                               @RequestParam("name") String name,
                               @RequestParam("file") MultipartFile file){
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(name));
                stream.write(bytes);
                stream.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
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
    public String addProductToCart(Model model, @RequestParam String id, Principal principal){

        return getIndex(model);
    }

}
