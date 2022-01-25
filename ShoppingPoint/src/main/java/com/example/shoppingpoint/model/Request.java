package com.example.shoppingpoint.model;

public class Request {
    private int requestId;
    private int productId;
    private float maxPrice;
    private int quantity;
    private boolean accepted;

    public Request(int requestId, int productId, float maxPrice, int quantity, boolean accepted) {
        setRequestId(requestId);
        setProductId(productId);
        setMaxPrice(maxPrice);
        setQuantity(quantity);
        setAccepted(accepted);
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
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

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }
}
