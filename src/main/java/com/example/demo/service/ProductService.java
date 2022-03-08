package com.example.demo.service;

import com.example.demo.entity.Product;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.persistence.Query;
import java.sql.*;

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
//        Product[] products;
//        int count = 0;
//        try {
//            if (!connection.isClosed()) {
//                preparedStatement = connection.prepareStatement(
//                        "select price, title from \"Products\";"
//                );
//                ResultSet rs = preparedStatement.executeQuery();
//                while (rs.next()) {
//                    String tmpTitle = rs.getString("title");
//                    int tmpPrice = rs.getInt("price");
//                    products[count] = new Product(tmpTitle,tmpPrice);
//                    count++;
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return products;
        session.beginTransaction();
        Product[] products = (Product[]) session.createQuery("select price, title from Products", Product.class).getResultList().toArray();
        session.getTransaction().commit();
        return  products;
    }

    @Bean
    @Scope("session")
    private void connect() throws SQLException {
        this.session = new Configuration()
                .addAnnotatedClass(Product.class)
                .buildSessionFactory()
                .getCurrentSession();
    }

}
