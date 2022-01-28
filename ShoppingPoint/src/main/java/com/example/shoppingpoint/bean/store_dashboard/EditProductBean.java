package com.example.shoppingpoint.bean.store_dashboard;

public class EditProductBean {
    private float price;
    private float discountedPrice;
    private int quantity;

    public EditProductBean(String price, String discountedPrice, String quantity) throws Exception {
        setPrice(Float.parseFloat(price));
        setDiscountedPrice(Float.parseFloat(discountedPrice));
        setQuantity(Integer.parseInt(quantity));
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) throws Exception {
        if (price < 0) throw new Exception("Invalid data");
        this.price = price;
    }

    public float getDiscountedPrice() {
        return discountedPrice;
    }

    public void setDiscountedPrice(float discountedPrice) throws Exception {
        if (discountedPrice < 0) throw new Exception("Invalid data");
        this.discountedPrice = discountedPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) throws Exception {
        if (quantity < 0) throw new Exception("Invalid data");
        this.quantity = quantity;
    }
}
