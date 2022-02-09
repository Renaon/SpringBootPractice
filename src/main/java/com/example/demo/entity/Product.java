package com.example.demo.entity;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.relational.core.mapping.Table;

@EntityScan
@Table("Product")
public class Product {
    private String title;
    private int price;
    private static int id;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public Product(){
        Product.id++;
    }
    public Product(String title, int price){
        this.title = title;
        this.price = price;
    }
}
