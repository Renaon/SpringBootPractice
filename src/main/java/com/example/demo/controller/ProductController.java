package com.example.demo.controller;

import com.example.demo.entity.Category;
import com.example.demo.entity.Product;
import com.example.demo.entity.ShopCart;
import com.example.demo.service.CartService;
import com.example.demo.service.ProductService;
import com.example.demo.service.UserService;
import com.example.demo.utils.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;
import java.util.Objects;

@Controller
public class ProductController {
    @Autowired
    private  ProductService productService;
    private  CartService cartService;
    private  UserService userService;

    private Product product;

    @GetMapping("/")
//    @ResponseBody
    public String getIndex(Model model,@PageableDefault Pageable pageable){
//        List<Product> products = productService.getAll();
        pageable =  PageRequest.of(1, 2, Sort.Direction.ASC, "date");
        Page<Product> page = productService.listToPage(productService.getAll());
        model.addAttribute("products", page.getContent());
        return "index";
     }

    @GetMapping("/add")
    public String add(Model model) {
        this.product = new Product();
        model.addAttribute("product", product);
        return "form";
    }

    @GetMapping("/addWithLogo")
    public String add(Model model, @RequestParam("filename") String filename) {
//        Product product = new Product();
        this.product.setLogo_path("src/main/webapp/upload/logo/" + filename);
        System.out.println("addWithLogo: " + this.product.getLogo_path());
        model.addAttribute("product", product);
        return "form";
    }

    @PostMapping("/success")
    public @ResponseBody String pcessAddress(Model model, @ModelAttribute("form") Product product){
        product.setLogo_path(this.product.getLogo_path());
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
    public String productdel(Model model, @RequestParam String id
            , Pageable pageable){
        productService.dropProduct(Integer.parseInt(id));
        return getIndex(model, pageable);
    }

    @GetMapping("/category")
    public String getCategoryProductString(Model model, @RequestParam String category){
        List<Product> product = productService.getProductsByCategory(category);
        model.addAttribute("products", product);
        return "category";
    }


    //дописать 2 метода для отображения корзины и для добавления в корзину
    @GetMapping("/buy")
    public String addProductToCart(Model model, @RequestParam String id, Principal principal
            , Pageable pageable, HttpSession session){
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.add(productService.getProductById(Integer.parseInt(id)));
        session.setAttribute("cart", shoppingCart);
        return getIndex(model, pageable);
    }

}
