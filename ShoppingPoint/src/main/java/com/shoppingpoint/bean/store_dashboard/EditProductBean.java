package com.shoppingpoint.bean.store_dashboard;

import com.shoppingpoint.exception.BeanException;

public class EditProductBean {
    private float newPrice;
    private float newDiscountedPrice;
    private int newQuantity;

    public EditProductBean(String price, String discountedPrice, String quantity) throws BeanException {
        setNewPrice(Float.parseFloat(price));
        setNewDiscountedPrice(Float.parseFloat(discountedPrice));
        setNewQuantity(Integer.parseInt(quantity));
    }

    public float getNewPrice() {
        return newPrice;
    }

    public void setNewPrice(float price) throws BeanException {
        if (price < 0) throw new BeanException("price", BeanException.MORE_THAN_ZERO_REASON);
        this.newPrice = price;
    }

    public float getNewDiscountedPrice() {
        return newDiscountedPrice;
    }

    public void setNewDiscountedPrice(float discountedPrice) throws BeanException {
        if (discountedPrice < 0) throw new BeanException("discounted price", BeanException.MORE_THAN_ZERO_REASON);
        this.newDiscountedPrice = discountedPrice;
    }

    public int getNewQuantity() {
        return newQuantity;
    }

    public void setNewQuantity(int quantity) throws BeanException {
        if (quantity < 0) throw new BeanException("quantity", BeanException.MORE_THAN_ZERO_REASON);
        this.newQuantity = quantity;
    }
}
