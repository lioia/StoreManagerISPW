package com.example.shoppingpoint.bean.add_product;

import com.example.shoppingpoint.exception.BeanException;

import java.io.InputStream;

public class AddProductCommonBean {
    private String name;
    private float price;
    private float discountedPrice;
    private int quantity;
    private String status;
    private InputStream image;

    public AddProductCommonBean(String name, float price, float discountedPrice, int quantity, String status) throws BeanException {
        setName(name);
        setPrice(price);
        setDiscountedPrice(discountedPrice);
        setQuantity(quantity);
        setStatus(status);
    }

    public AddProductCommonBean(String name, String price, String discountedPrice, String quantity, String status) throws BeanException {
        this(name, Float.parseFloat(price), Float.parseFloat(discountedPrice), Integer.parseInt(quantity), status);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) throws BeanException {
        if (price < 0) throw new BeanException("price", "it has to be more than 0");
        this.price = price;
    }

    public float getDiscountedPrice() {
        return discountedPrice;
    }

    public void setDiscountedPrice(float discountedPrice) throws BeanException {
        if (discountedPrice < 0) throw new BeanException("discounted price", "it has to be more than 0");
        this.discountedPrice = discountedPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) throws BeanException {
        if (quantity < 0) throw new BeanException("quantity", "it has to be more than 0");

        this.quantity = quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public InputStream getImage() {
        return image;
    }

    public void setImage(InputStream image) {
        this.image = image;
    }
}
