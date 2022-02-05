package com.example.shoppingpoint.model;

public class LoyaltyCard {
    private final int points;
    private final String storeName;

    public LoyaltyCard(Integer points, String storeName) {
        this.points = points;
        this.storeName = storeName;
    }

    public Integer getPoints() {
        return points;
    }

    public String getStoreName() {
        return storeName;
    }

}
