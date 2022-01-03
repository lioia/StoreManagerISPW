package com.example.shoppingpoint.model.product;

import com.example.shoppingpoint.utils.StatusType;
import javafx.scene.image.Image;

public abstract class Product {
    private Integer id;
    private String name;
    private Number price;
    private Number discountedPrice;
    private Integer quantity;
    private StatusType status;
    private Image image;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Number getPrice() {
        return price;
    }

    public void setPrice(Number price) {
        this.price = price;
    }

    public Number getDiscountedPrice() {
        return discountedPrice;
    }

    public void setDiscountedPrice(Number discountedPrice) {
        this.discountedPrice = discountedPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public StatusType getStatus() {
        return status;
    }

    public void setStatus(StatusType status) {
        this.status = status;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
