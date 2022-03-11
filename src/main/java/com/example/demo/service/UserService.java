package com.example.demo.service;

import com.example.demo.entity.Role;
import com.example.demo.entity.Users;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;


@Service
public class UserService implements UserDetailsService {
    private Session session = new Configuration()
            .addAnnotatedClass(Users.class)
            .buildSessionFactory()
            .getCurrentSession();

    public int getRole(String role){
        session = new Configuration()
                .addAnnotatedClass(Role.class)
                .buildSessionFactory()
                .getCurrentSession();
        session.beginTransaction();
        Integer roleFromDB = session.createQuery("select id from role where name='" + role + "'", Integer.class).getSingleResult();
//        int result = roleFromDB.getId();
        session.getTransaction().commit();
        return roleFromDB;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        session.beginTransaction();
        Users user;
        try {
            Integer usId = session
                    .createQuery("select id from Users where login='" + username + "'", Integer.class).getSingleResult();
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

    public User findUserById(int id){
        return session.get(User.class, id);
    }

    public boolean saveUser(Users user) {
        try {
            loadUserByUsername(user.getLogin());
            return false;
        } catch (UsernameNotFoundException  e) {
            session.save(user);
            session.getTransaction().commit();
            return true;
        }
    }

    public boolean deleteUser(int id) {
        if (findUserById(id) != null){
            session.remove(findUserById(id));
            return true;
        }
        else return false;
    }

    public static void main(String[] args) {
        new UserService().saveUser(new Users("huita"));
    }

}
