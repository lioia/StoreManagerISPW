package com.example.shoppingpoint.bean;

public class NewRequestBean {
    private float maxPrice;
    private int quantity;

    public NewRequestBean(String maxPrice, String quantity) throws Exception {
        setMaxPrice(Float.parseFloat(maxPrice));
        setQuantity(Integer.parseInt(quantity));
    }

    public float getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(float maxPrice) throws Exception {
        if(maxPrice <= 0) {
//            TODO exception
            throw new Exception("Invalid data");
        }
        this.maxPrice = maxPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) throws Exception {
        if(quantity <= 0) {
//            TODO exception
            throw new Exception("Invalid data");
        }
        this.quantity = quantity;
    }
}
