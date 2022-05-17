package com.example.demo.controller;

import com.example.demo.entity.Category;
import com.example.demo.entity.Product;
import com.example.demo.entity.ShopCart;
import com.example.demo.service.CartService;
import com.example.demo.service.FileService;
import com.example.demo.service.ProductService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Controller
public class ProductController {
    @Autowired
    private  ProductService productService;
    private  CartService cartService;
    private  UserService userService;
    private FileService fileService;

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
    public @ResponseBody String pcessAddress(@ModelAttribute("form") Product product){
        productService.addProduct(product);
        return "success";
    }

    @PostMapping("/uploadlogo")
    public String uploadLogo(@RequestParam("file") MultipartFile file,
                             RedirectAttributes redirectAttributes){
        fileService.uploadFile(file);
        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");
        return "redirect:/add";
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
