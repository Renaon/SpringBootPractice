package com.example.demo.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "ShopCart")
public class ShopCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @OneToOne
    @PrimaryKeyJoinColumn
    @Column(name = "user_id")
    private Integer user_id;

    @ManyToMany
    @PrimaryKeyJoinColumn
    @Column(name = "product_id")
    private List<Product> product_id;

    public ShopCart() {

    }

    public ShopCart(Integer user_id, List<Product> product_id) {
        this.user_id = user_id;
        this.product_id = product_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public List<Product> getProduct_id() {
        return product_id;
    }

    public void setProduct_id(List<Product> product_id) {
        this.product_id = product_id;
    }
}
