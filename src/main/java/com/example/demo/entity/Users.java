package com.example.demo.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity()
@Table(name = "Users")
public class Users implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "login")
    private String login;

    @OneToOne
    @PrimaryKeyJoinColumn
    @JoinColumn(name = "role_id")
    private Role role_id;

    @ManyToMany
    @JoinTable(name = "ShopCart",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "cart_id"))
    List<ShopCart> shopCart;

    public List<ShopCart> getShopCart() {
        return shopCart;
    }

    public void setShopCart(List<ShopCart> shopCart) {
        this.shopCart = shopCart;
    }

    @Column(name = "password")
    private String password;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Role getRole_id() {
        return role_id;
    }

    public void setRole_id(Role role_id) {
        this.role_id = role_id;
    }

    public Users(String login) {
        this.login = login;
    }

    public Users(){}

//    public Users(String login, String role, String password) {
//        this.login = login;
//        this.role_id = new UserService().getRole(role);
//        this.password = password;
//    }

    public Users(String login, String password) {
        this.login = login;
        this.password = password;
        this.role_id.setId(0);
    }

    public String getLogin() {
        return login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
