package com.example.demo.service;

import com.example.demo.entity.*;
import com.example.demo.utils.ShoppingCart;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class CartService {
    private Session session;

    private void connect() {
        session = new Configuration()
                .addAnnotatedClass(ShopCart.class)
                .addAnnotatedClass(Users.class)
                .addAnnotatedClass(Role.class)
                .addAnnotatedClass(Product.class)
                .addAnnotatedClass(Category.class)
                .buildSessionFactory()
                .getCurrentSession();
        session.beginTransaction();
    }

    public static void main(String[] args) {
        new CartService().connect();
    }

    public void buy(ShopCart cart) {
        System.out.println("ID продукта" + cart.getProduct_id()
         + " ID покупателя" + cart.getUser_id());
        connect();
        session.saveOrUpdate(cart);
        session.getTransaction().commit();
        session.close();
    }

    public ShoppingCart getCart(HttpSession session){
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
        if (cart == null) {
            cart = new ShoppingCart();
            session.setAttribute("cart", cart);
        }
        return cart;
    }
}
