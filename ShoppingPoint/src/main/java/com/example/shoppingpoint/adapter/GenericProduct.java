package com.example.shoppingpoint.adapter;

import javafx.scene.image.Image;

public interface GenericProduct {
    Integer getId();
    String getName();
    Float getPrice();
    Float getDiscountedPrice();
    Integer getQuantity();
    Image getImage();
    String getStatus();
    String getDescription();

    void setPrice(float price);
    void setDiscountedPrice(float discountedPrice);
    void setQuantity(int quantity);
}
