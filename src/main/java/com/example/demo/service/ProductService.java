package com.example.demo.service;

import com.example.demo.entity.Product;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.sql.*;

@Service
public class ProductService {
    private static Connection connection;
    private static Statement stmt;
    private PreparedStatement preparedStatement;

    public ProductService(){
        try{
            connect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addProduct(Product product){
        try {
            if (!connection.isClosed()) {
                preparedStatement = connection.prepareStatement(
                        "insert into \"Products\"(price,title) values (?,?);"
                );
                preparedStatement.setInt(1, product.getPrice());
                preparedStatement.setString(2, product.getTitle());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
        Product[] products = new Product[getSize()];
        int count = 0;
        try {
            if (!connection.isClosed()) {
                preparedStatement = connection.prepareStatement(
                        "select price, title from \"Products\";"
                );
                ResultSet rs = preparedStatement.executeQuery();
                while (rs.next()) {
                    String tmpTitle = rs.getString("title");
                    int tmpPrice = rs.getInt("price");
                    products[count] = new Product(tmpTitle,tmpPrice);
                    count++;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    @Bean
    @Scope("session")
    private void connect() throws SQLException {
        connection = DriverManager.getConnection("jdbc:sqlite:src/main/resources/HW3ProductsRepos.db");
        stmt = connection.createStatement();
    }

}
