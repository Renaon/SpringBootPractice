package com.example.demo.controller;

import com.example.demo.entity.Product;
import com.example.demo.service.ProductService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ProductController {
    private final ProductService productService = new ProductService();
//    SessionFactory sessionFactory = new Configuration()
//            .addAnnotatedClass(Product.class)
//            .buildSessionFactory();
//    Session session = sessionFactory.getCurrentSession();
        Session session = new Configuration()
            .addAnnotatedClass(Product.class)
            .buildSessionFactory()
            .getCurrentSession();

    @GetMapping("/")
//    @ResponseBody
    public String getAll(Model model){
        Product[] products = productService.getAll();
        model.addAttribute("products", products);
        return "store";
    }

    @GetMapping("/add")
    public String add(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        return "form";
    }

    @RequestMapping("/success")
    public String pcessAddress(@ModelAttribute("form") Product product){
        session.beginTransaction();
        session.save(product);
        session.getTransaction().commit();
//        productService.addProduct(product);
        return "success";
    }
}
