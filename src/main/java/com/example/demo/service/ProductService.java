package com.example.demo.service;

import com.example.demo.entity.Category;
import com.example.demo.entity.Product;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.*;
import java.util.List;

@Service
public class ProductService {
    private static Connection connection;
    private static Statement stmt;
    private PreparedStatement preparedStatement;
    private Session session;

    public ProductService(){
        try{
            connect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addProduct(Product product){
        session.beginTransaction();
        session.save(product);
        session.getTransaction().commit();
    }

    private int getSize() {
        String result = null;
        try {
            if (!connection.isClosed()) {
                preparedStatement = connection.prepareStatement(
                        "select count(id) from \"Products\";"
                );
                ResultSet rs = preparedStatement.executeQuery();
                while (rs.next()) {
                    result = rs.getString("count(id)");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        assert result != null;
        return Integer.parseInt(result);
    }

    public Product[] getAll() {
        try {
            connect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        session.beginTransaction();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery(Product.class);
        Root root = cq.from(Product.class);
        CriteriaQuery all = cq.select(root);

        TypedQuery allQuery = session.createQuery(all);
        List<Product> productList = allQuery.getResultList();
        Product[] products = new Product[productList.size()+1];
        for (Product product : productList){
            products[product.getId()] = product;
        }
        session.getTransaction().commit();
        return products;
    }

    public Category[] getCategories(){
        try {
            connect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        session.beginTransaction();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery(Category.class);
        Root root = cq.from(Category.class);
        CriteriaQuery all = cq.select(root);

        TypedQuery allQuery = session.createQuery(all);
        List<Category> categoryList = allQuery.getResultList();
        Category[] categories = new Category[categoryList.size()+1];
        for (Category i : categoryList){
            categories[i.getId()] = i;
        }
        session.getTransaction().commit();
        return categories;
    }

    @Bean
    @Scope("session")
    private void connect() throws SQLException {
        this.session = new Configuration()
                .addAnnotatedClass(Product.class)
                .addAnnotatedClass(Category.class)
                .buildSessionFactory()
                .getCurrentSession();
    }

}
