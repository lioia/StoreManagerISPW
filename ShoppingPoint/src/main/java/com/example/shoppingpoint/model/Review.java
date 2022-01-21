package com.example.shoppingpoint.model;

public class Review {
    private int reviewId;
    private float value;
    private String clientName;
    private int productId;

    public Review(int reviewId, float value, String clientName, int productId) {
        setReviewId(reviewId);
        setValue(value);
        setClientName(clientName);
        setProductId(productId);
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }
}
