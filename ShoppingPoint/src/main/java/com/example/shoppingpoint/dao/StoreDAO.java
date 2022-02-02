package com.example.shoppingpoint.dao;

import com.example.shoppingpoint.exception.DatabaseException;
import com.example.shoppingpoint.model.Store;
import com.example.shoppingpoint.utils.StoreType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StoreDAO {
    private StoreDAO() {
        throw new IllegalStateException();
    }

    public static List<Store> getAllStores() throws SQLException {
        ArrayList<Store> stores = new ArrayList<>();

        // Create Connection
        Connection connection = DriverManager.getConnection(Database.DB_URL, Database.USER, Database.PASS);
        // Create statement
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        // Execute query
        ResultSet rs = statement.executeQuery("SELECT * FROM Store");
        while (rs.next()) {
            Store store = getStore(rs);
            stores.add(store);
        }

        rs.close();
        statement.close();
        connection.close();
        return stores;
    }

    public static Store getStoreByName(String name) throws SQLException, DatabaseException {
        // Create Connection
        Connection connection = DriverManager.getConnection(Database.DB_URL, Database.USER, Database.PASS);
        // Create statement
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        // Get user with specified username
        String sql = String.format("SELECT * FROM Store WHERE Name='%s'", name);
        // Execute query
        ResultSet rs = statement.executeQuery(sql);
        // Empty result
        if (!rs.first())
            throw new DatabaseException("store");

        rs.first();
        Store store = getStore(rs);

        rs.close();
        statement.close();
        connection.close();
        return store;
    }

    public static String getStoreOwnerUsernameByStoreName(String storeName) throws SQLException, DatabaseException {
        // Create Connection
        Connection connection = DriverManager.getConnection(Database.DB_URL, Database.USER, Database.PASS);
        // Create statement
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        // Get store with specified store owner
        String sql = String.format("SELECT StoreOwner FROM Store WHERE Name='%s'", storeName);
        // Execute query
        ResultSet rs = statement.executeQuery(sql);
        // Empty result
        if (!rs.first())
            throw new DatabaseException("store owner username");

        rs.first();
        String storeOwnerUsername = rs.getString("StoreOwner");

        rs.close();
        statement.close();
        connection.close();
        return storeOwnerUsername;
    }

    public static Store getStoreByStoreOwnerUsername(String username) throws SQLException, DatabaseException {
        // Create Connection
        Connection connection = DriverManager.getConnection(Database.DB_URL, Database.USER, Database.PASS);
        // Create statement
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        // Get store with specified store owner
        String sql = String.format("SELECT * FROM Store WHERE StoreOwner='%s'", username);
        // Execute query
        ResultSet rs = statement.executeQuery(sql);
        // Empty result
        if (!rs.first())
            throw new DatabaseException("store of " + username);

        rs.first();
        Store store = getStore(rs);

        rs.close();
        statement.close();
        connection.close();
        return store;
    }

    public static void saveStore(String name, String address, StoreType type, String storeOwner) throws SQLException {
        // Create Connection
        Connection connection = DriverManager.getConnection(Database.DB_URL, Database.USER, Database.PASS);
        // Create statement
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        // Get user with specified username
        String sql = String.format("INSERT INTO Store (Name, Address, Type, PointsInEuro, EuroInPoints,StoreOwner) VALUES ('%s', '%s', '%s', '%d', '%d','%s')", name, address, type, 0, 0, storeOwner);
        // Execute query
        statement.executeUpdate(sql);
        statement.close();
        connection.close();
    }

    public static void updatePoints(int pointsInEuro, int euroInPoints, String storeName) throws SQLException {
        // Create Connection
        Connection connection = DriverManager.getConnection(Database.DB_URL, Database.USER, Database.PASS);
        // Create statement
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        // Get user with specified username
        String sql = String.format("UPDATE Store SET PointsInEuro = %d, EuroInPoints = %d WHERE Name = '%s'", pointsInEuro, euroInPoints, storeName);
        // Execute query
        statement.executeUpdate(sql);
        statement.close();
        connection.close();
    }

    private static Store getStore(ResultSet rs) throws SQLException {
        String name = rs.getString("Name");
        String address = rs.getString("Address");
        Integer pointsInEuro = rs.getInt("PointsInEuro");
        Integer euroInPoints = rs.getInt("EuroInPoints");
        StoreType type = StoreType.valueOf(rs.getString("Type"));
        return new Store(name, address, pointsInEuro, euroInPoints, type);
    }
}
