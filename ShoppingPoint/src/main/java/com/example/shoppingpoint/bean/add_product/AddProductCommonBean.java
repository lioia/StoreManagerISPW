package com.example.shoppingpoint.bean.add_product;

public class AddProductCommonBean {
    private String name;
    private float price;
    private float discountedPrice;
    private int quantity;
    private String status;

    public AddProductCommonBean(String name, float price, float discountedPrice, int quantity, String status) throws Exception {
        setName(name);
        setPrice(price);
        setDiscountedPrice(discountedPrice);
        setQuantity(quantity);
        setStatus(status);
    }

    public AddProductCommonBean(String name, String price, String discountedPrice, String quantity, String status) throws Exception {
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

    public void setPrice(float price) throws Exception {
        if (price < 0) {
//            TODO throws Exception
            throw new Exception("Invalid data");
        }
        this.price = price;
    }

    public float getDiscountedPrice() {
        return discountedPrice;
    }

    public void setDiscountedPrice(float discountedPrice) throws Exception {
        if (discountedPrice < 0) {
//            TODO throws Exception
            throw new Exception("Invalid data");
        }
        this.discountedPrice = discountedPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) throws Exception {
        if (quantity < 0) {
//            TODO throws Exception
            throw new Exception("Invalid data");
        }

        this.quantity = quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
