package com.example.shoppingpoint.dao;

public class Database {
    private Database() {
        throw new IllegalStateException();
    }

    public static final String USER = "shoppingpoint";
    public static final String PASS = "password";
    public static final String DB_URL = "jdbc:mysql://localhost:3306/ShoppingPoint-DB";
    public static final String DRIVER_CLASS_NAME = "com.mysql.jdbc.Driver";
}
