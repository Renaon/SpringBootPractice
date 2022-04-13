package com.example.demo.service;

import com.example.demo.entity.Category;
import com.example.demo.entity.Product;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static javax.transaction.Transactional.TxType.REQUIRES_NEW;

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
        try {
            connect();
            session.beginTransaction();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        int  id = getCategoryIdByName(product.getCategory().getName());
        if(id == 0) session.saveOrUpdate(product);
        else {
            Category exists =  session.get(Category.class, id);
            product.setCategory(exists);
            session.saveOrUpdate(product);
        }
        session.getTransaction().commit();
        session.close();
    }

    public void dropProduct(Integer productId){
        try {
            connect();
            session.beginTransaction();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Product tmp = session.get(Product.class, productId);
        Category tmpCategory = new Category("temp");
        session.save(tmpCategory);
        tmp.setCategory(tmpCategory);
        session.remove(tmp);
        session.getTransaction().commit();
        session.close();
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

    public List<Product> getAll() {
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
//        Product[] products = new Product[productList.size()+1];
//        for (Product product : productList){
//            products[product.getId()] = product;
//        }
        session.getTransaction().commit();
        session.close();
        return productList;
    }

    public List<Category> getCategories(){
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
//        Category[] categories = new Category[categoryList.size()+1];
//        for (Category i : categoryList){
//            categories[i.getId()] = i;
//        }
        session.getTransaction().commit();
        session.close();
        return categoryList;
    }

    @Transactional(REQUIRES_NEW)
    public List<Product> getProductsByCategory(String category){
        try {
            connect();
            session.beginTransaction();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String stringQuery =
                "SELECT\n" +
                        "\tProducts.id\n" +
                        "FROM\n" +
                        "\tProducts\n" +
                        "\tINNER JOIN\n" +
                        "\tgb_Category\n" +
                        "\tON \n" +
                        "\t\tProducts.category_id = gb_Category.id\n" +
                        "WHERE\n" +
                        "\tgb_Category.name = '" + category + "'";
        Query query = session.createSQLQuery(stringQuery);
        List<Integer> result = query.getResultList();
        List<Product> products = new ArrayList<>();
//        Product[] products = new Product[result.size()];
//        for (int i = 0; i <result.size(); i++){
//            products[i] = session.get(Product.class, result.get(i));
//        }
        for (Integer i: result){
            products.add(session.get(Product.class, i));
        }
        session.getTransaction().commit();
        session.close();
        return products;
    }

    private Integer getCategoryIdByName(String name){
//        try {
//            connect();
//            session.beginTransaction();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        try {
            String stringQuery = "SELECT id FROM gb_Category WHERE name='" + name + "'";
            Integer id = session.createQuery(stringQuery, Integer.class).getSingleResult();
            return id;
        } catch (NoResultException e) {
            return 0;
        }


//        session.getTransaction().commit();

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
