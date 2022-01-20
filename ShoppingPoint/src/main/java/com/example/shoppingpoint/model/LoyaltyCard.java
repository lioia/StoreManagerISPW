package com.example.shoppingpoint.model;

public class LoyaltyCard {
    private Integer points;
    private final String clientUsername;
    private final String storeName;

    public LoyaltyCard(Integer points, String clientUsername, String storeName) {
        this.points = points;
        this.storeName = storeName;
        this.clientUsername = clientUsername;
    }

    public Integer getPoints() {
        return points;
    }

    public void addPoints(Integer pointsToAdd) {
        points += pointsToAdd;
    }

    public void removePoints(Integer pointsToRemove) {
        points -= pointsToRemove;
    }

    public String getStoreName() {
        return storeName;
    }

    public String getClientUsername() {
        return clientUsername;
    }
}
