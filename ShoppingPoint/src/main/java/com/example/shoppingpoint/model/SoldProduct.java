package com.example.shoppingpoint.model;

import com.example.shoppingpoint.model.product.Product;

import java.time.LocalDate;

public class SoldProduct {
    private Product product;
    private LocalDate date;
    private Integer quantity;

    public SoldProduct(Product product, LocalDate date, Integer quantity) {
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
}
