package com.example.shoppingpoint.bean.store_dashboard;

import com.example.shoppingpoint.exception.BeanException;

public class EditProductBean {
    private float price;
    private float discountedPrice;
    private int quantity;

    public EditProductBean(String price, String discountedPrice, String quantity) throws BeanException {
        setPrice(Float.parseFloat(price));
        setDiscountedPrice(Float.parseFloat(discountedPrice));
        setQuantity(Integer.parseInt(quantity));
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) throws BeanException {
        if (price < 0) throw new BeanException("price", BeanException.MORE_THAN_ZERO_REASON);
        this.price = price;
    }

    public float getDiscountedPrice() {
        return discountedPrice;
    }

    public void setDiscountedPrice(float discountedPrice) throws BeanException {
        if (discountedPrice < 0) throw new BeanException("discounted price", BeanException.MORE_THAN_ZERO_REASON);
        this.discountedPrice = discountedPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) throws BeanException {
        if (quantity < 0) throw new BeanException("quantity", BeanException.MORE_THAN_ZERO_REASON);
        this.quantity = quantity;
    }
}
