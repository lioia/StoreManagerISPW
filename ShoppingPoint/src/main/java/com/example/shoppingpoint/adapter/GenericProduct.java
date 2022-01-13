package com.example.shoppingpoint.adapter;

import com.example.shoppingpoint.utils.StatusType;
import javafx.scene.image.Image;

public interface GenericProduct {
    String getName();
    Float getPrice();
    Float getDiscountedPrice();
    Integer getQuantity();
    Image getImage();
    StatusType getStatus();
    String getDescription();
}
