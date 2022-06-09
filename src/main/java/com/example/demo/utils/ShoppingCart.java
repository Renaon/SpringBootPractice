package com.example.demo.utils;

import com.example.demo.entity.OrderItem;
import com.example.demo.entity.Product;
import com.example.demo.entity.ShopCart;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ShoppingCart {
    private List<OrderItem> items;
    private  Double totalPrice;
    private ShopCart cart;

    public ShoppingCart() {
        items = new ArrayList<>();
        totalPrice = 0.0;
    }

    public void add(Product product){
        OrderItem orderItem = findOrderFromProduct(product);
        if (orderItem == null) {
            orderItem = new OrderItem();
            orderItem.setProduct(product);
            orderItem.setItemPrice( (double) product.getPrice());
            orderItem.setQuantity(0L);
            orderItem.setTotalPrice(0.0);
            orderItem.setId(0);
            items.add(orderItem);
        }
        orderItem.setQuantity(orderItem.getQuantity() + 1);
        recalculate();
    }

    public void remove(Product product) {
        OrderItem orderItem = findOrderFromProduct(product);
        if (orderItem == null) {
            return;
        }
        items.remove(orderItem);
        recalculate();
    }

    private void recalculate() {
        totalPrice = 0.0;
        for (OrderItem o : items) {
            o.setTotalPrice((double) (o.getQuantity() * o.getProduct().getPrice()));
            totalPrice += o.getTotalPrice();
        }
    }

    public void setQuantity(Product product, Long quantity) {
        OrderItem orderItem = findOrderFromProduct(product);
        if (orderItem == null) {
            return;
        }
        orderItem.setQuantity(quantity);
        recalculate();
    }

    public ShopCart getCart() {
        return this.cart;
    }

    public void setCart(ShopCart cart) {
        this.cart = cart;
    }

    public List<OrderItem> getItems() {
        return this.items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    public Double getTotalPrice() {
        return this.totalPrice;
    }

    private OrderItem findOrderFromProduct(Product product) {
        return items.stream().filter(o -> o.getProduct().getId()==(product.getId())).findFirst().orElse(null);
    }
}
