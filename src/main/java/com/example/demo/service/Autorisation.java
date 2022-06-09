package com.example.demo.service;

import com.example.demo.entity.Product;
import com.example.demo.entity.Role;
import com.example.demo.entity.Users;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

import javax.persistence.Query;

public class Autorisation {

    public static int getRole(String role){
        Session session = new Configuration()
                .addAnnotatedClass(Role.class)
                .buildSessionFactory()
                .getCurrentSession();
        session.beginTransaction();
        Integer roleFromDB = session.createQuery("select id from role where name=:role", Integer.class)
                .setParameter("role", role).getSingleResult();
//        int result = roleFromDB.getId();
        session.getTransaction().commit();
        return roleFromDB;
    }

    public static void main(String[] args) {
        System.out.println(getRole("admin"));
    }
}
