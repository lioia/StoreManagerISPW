package com.shoppingpoint.model;

public class ClientListData {
    private String username;
    private String email;
    private int points;

    public ClientListData(String username, String email, int points){
        setPoints(points);
        setEmail(email);
        setUsername(username);
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getPoints() {
        return points;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }
}
