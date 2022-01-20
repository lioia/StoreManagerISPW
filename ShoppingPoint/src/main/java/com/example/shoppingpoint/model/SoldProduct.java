package com.example.shoppingpoint.model;

import com.example.shoppingpoint.model.product.Product;
import com.example.shoppingpoint.model.user.Client;

import java.time.LocalDate;

public class SoldProduct {
    private Product product;
    private Client client;
    private LocalDate date;
    private Integer quantity;

    public SoldProduct(Client client, Product product, LocalDate date, Integer quantity) {
        setClient(client);
        setProduct(product);
        setDate(date);
        setQuantity(quantity);
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
