package com.example.demo.entity;

import com.example.demo.service.Autorisation;

import javax.persistence.*;

@Entity(name = "Users")
@Table(name = "Users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "login")
    private String login;

    @Column(name = "role_id")
    private int role_id;

    @Column(name = "password")
    private String password;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Users(String login) {
        this.login = login;
    }

    public Users(){}

    public Users(String login, String role, String password) {
        this.login = login;
        this.role_id = Autorisation.getRole(role);
        this.password = password;
    }
}
