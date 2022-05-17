package com.example.demo.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "ShopCart")
public class ShopCart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "user_id")
    private Integer user_id;

    @Column(name = "product_id")
    private Integer product_id;

    @OneToOne
    @PrimaryKeyJoinColumn
    @JoinColumn(name = "user_id")
    private Users user;

//    @OneToMany
//    @PrimaryKeyJoinColumn
//    @JoinColumn(name = "product_id")
//    private List<Product> product;

    public ShopCart() {
    }

//    public ShopCart(Integer user_id, Integer product_id) {
//        this.user_id = user_id;
//        this.product_id = product_id;
//    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Integer product_id) {
        this.product_id = product_id;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
