package com.example.demo.service;

import com.example.demo.entity.Role;
import com.example.demo.entity.Users;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;


@Service
public class UserService implements UserDetailsService {
    private Session session;

    public int getRole(String role){
        session = new Configuration()
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

    @Override
    public Users loadUserByUsername(String username) throws UsernameNotFoundException {
        session = new Configuration()
                .addAnnotatedClass(Users.class)
                .addAnnotatedClass(Role.class)
                .buildSessionFactory()
                .getCurrentSession();
        session.beginTransaction();
        Users user;
        try {
            Integer usId = session
                    .createQuery("select id from Users where login=:username", Integer.class)
                    .setParameter("username", username).getSingleResult();
            user = session.get(Users.class, usId);
            session.getTransaction().commit();
        } catch (NoResultException e) {
            throw new UsernameNotFoundException("User not found");
        }

//        if (user == null) {
//            throw new UsernameNotFoundException("User not found");
//        }
        return user;
    }

    public Users findUserById(int id){
        session = new Configuration()
            .addAnnotatedClass(Users.class)
            .buildSessionFactory()
            .getCurrentSession();

        session.beginTransaction();
        return session.get(Users.class, id);
    }

    public boolean saveUser(Users user) {
        try {
            loadUserByUsername(user.getLogin());
            return false;
        } catch (UsernameNotFoundException  e) {
            session = new Configuration()
                    .addAnnotatedClass(Users.class)
                    .buildSessionFactory()
                    .getCurrentSession();
            session.saveOrUpdate(user);
            session.getTransaction().commit();
            return true;
        }
    }

    public boolean deleteUser(int id) {
        session = new Configuration()
                .addAnnotatedClass(Users.class)
                .buildSessionFactory()
                .getCurrentSession();
        session.beginTransaction();
        Users del = findUserById(id);
        if (del != null){
            session.remove(del);
            session.getTransaction().commit();
            return true;
        }
        else return false;
    }

    public boolean loginUser(Users user){
        try {
            loadUserByUsername(user.getLogin());
            return true;
        }catch (UsernameNotFoundException e) {
            return false;
        }
    }

    public static void main(String[] args) {
        UserService us = new UserService();
        Users user =  us.loadUserByUsername("Lennon");
        us.deleteUser(user.getId());
    }

}
