package com.shoppingpoint.model;

import com.shoppingpoint.adapter.GenericProduct;

public class Request {
    private int requestId;
    private GenericProduct product;
    private float maxPrice;
    private int quantity;
    private boolean accepted;

    public Request(int requestId, GenericProduct product, float maxPrice, int quantity, boolean accepted) {
        setRequestId(requestId);
        setProduct(product);
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

    public GenericProduct getProduct() {
        return product;
    }

    public void setProduct(GenericProduct product) {
        this.product = product;
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
