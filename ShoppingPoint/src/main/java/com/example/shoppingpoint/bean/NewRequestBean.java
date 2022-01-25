package com.example.shoppingpoint.bean;

public class NewRequestBean {
    private float maxPrice;
    private int quantity;

    public NewRequestBean(String maxPrice, String quantity) {
        setMaxPrice(Float.parseFloat(maxPrice));
        setQuantity(Integer.parseInt(quantity));
    }

    public float getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(float maxPrice) {
        this.maxPrice = maxPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
