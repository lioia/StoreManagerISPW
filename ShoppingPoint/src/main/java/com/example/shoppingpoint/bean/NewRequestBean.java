package com.example.shoppingpoint.bean;

import com.example.shoppingpoint.exception.BeanException;

public class NewRequestBean {
    private float maxPrice;
    private int quantity;

    public NewRequestBean(String maxPrice, String quantity) throws BeanException {
        setMaxPrice(Float.parseFloat(maxPrice));
        setQuantity(Integer.parseInt(quantity));
    }

    public float getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(float maxPrice) throws BeanException {
        if (maxPrice < 0) throw new BeanException("max price", "it has to be more than 0");
        this.maxPrice = maxPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) throws BeanException {
        if (quantity < 0) throw new BeanException("quantity", "it has to be more than 0");
        this.quantity = quantity;
    }
}
