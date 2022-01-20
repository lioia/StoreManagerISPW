package com.example.shoppingpoint.adapter;

import javafx.scene.image.Image;

public interface GenericProduct {
    String getName();
    Float getPrice();
    Float getDiscountedPrice();
    Integer getQuantity();
    Image getImage();
    String getStatus();
    String getDescription();
}
