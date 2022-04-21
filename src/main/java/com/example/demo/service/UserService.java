package com.example.demo.service;

import com.example.demo.entity.Role;
import com.example.demo.entity.ShopCart;
import com.example.demo.entity.Users;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.*;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;


@Service
public class UserService implements UserDetailsService {
    private Session session;

    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    private void connect() {
        session = new Configuration()
                .addAnnotatedClass(Users.class)
                .addAnnotatedClass(Role.class)
                .addAnnotatedClass(ShopCart.class)
                .buildSessionFactory()
                .getCurrentSession();
        session.beginTransaction();
    }

    public int getRole(String role){
        connect();
        Integer roleFromDB = session.createQuery("select id from role where name=:role", Integer.class)
                .setParameter("role", role).getSingleResult();
//        int result = roleFromDB.getId();
        session.getTransaction().commit();
        session.close();
        return roleFromDB;
    }

    @Override
    public Users loadUserByUsername(String username) throws UsernameNotFoundException {
        connect();
        Users user;
        try {
            Integer usId = session
                    .createQuery("select id from Users where login=:username", Integer.class)
                    .setParameter("username", username).getSingleResult();
            user = session.get(Users.class, usId);
            session.getTransaction().commit();
            session.close();
        } catch (NoResultException e) {
            session.close();
            throw new UsernameNotFoundException("User not found");
        }

//        if (user == null) {
//            throw new UsernameNotFoundException("User not found");
//        }
        return user;
    }

    public Users findUserById(int id){
       connect();
        Users found = session.get(Users.class, id);
        session.close();
        return found;
    }

    public boolean saveUser(Users user) {
        try {
            loadUserByUsername(user.getLogin());
            return false;
        } catch (UsernameNotFoundException  e) {
            connect();
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            session.saveOrUpdate(user);
            session.getTransaction().commit();
            session.close();
            return true;
        }
    }

    public boolean deleteUser(int id) {
        Users del = findUserById(id);
        if (del != null){
            connect();
            session.remove(del);
            session.getTransaction().commit();
            session.close();
            return true;
        }
        else{
            session.close();
            return false;
        }
    }

    //в методе ниже чет не так
    public boolean loginUser(Users user){
        try {
            Users userFromDB = loadUserByUsername(user.getLogin());
            return userFromDB.getPassword().equals(bCryptPasswordEncoder.encode(user.getPassword()));
        }catch (UsernameNotFoundException e) {
            return false;
        }
    }

    public List<Users> allUsers() {
        connect();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery(Users.class);
        Root root = cq.from(Users.class);
        CriteriaQuery all = cq.select(root);

        TypedQuery allQuery = session.createQuery(all);
        List<Users> usersList = allQuery.getResultList();
        session.getTransaction().commit();
        session.close();
        return usersList;
    }

    public static void main(String[] args) {
        UserService us = new UserService();
        Users user =  us.loadUserByUsername("Lennon");
    }

}
