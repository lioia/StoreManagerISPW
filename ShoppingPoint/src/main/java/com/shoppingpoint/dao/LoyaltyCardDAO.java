package com.shoppingpoint.dao;

import com.shoppingpoint.exception.DatabaseException;
import com.shoppingpoint.model.ClientListData;
import com.shoppingpoint.model.LoyaltyCard;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LoyaltyCardDAO {
    private LoyaltyCardDAO() {
        throw new IllegalStateException();
    }

    public static LoyaltyCard getLoyaltyCardFromClientAndStoreName(String client, String storeName) throws SQLException, DatabaseException {
        // Create Connection
        LoyaltyCard card;
        try (Connection connection = DriverManager.getConnection(Database.DB_URL, Database.USER, Database.PASS)) {
            // Create statement
            try (Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
                // Get user with specified username
                String sql = String.format("SELECT * FROM LoyaltyCard WHERE Client='%s' AND Store = '%s'", client, storeName);
                // Execute query
                ResultSet rs = statement.executeQuery(sql);
                // Empty result
                if (!rs.first()) {
                    rs.close();
                    return null;
                }

                rs.first();
                Integer points = rs.getInt("Points");
                card = new LoyaltyCard(points, storeName);
                rs.close();
            }
        }
        return card;
    }

    public static void saveLoyaltyCard(String client, String storeName, Integer points) throws SQLException {
        // Create Connection
        try (Connection connection = DriverManager.getConnection(Database.DB_URL, Database.USER, Database.PASS)) {
            // Create statement
            try (Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
                // Get user with specified username
                String sql = String.format("INSERT INTO LoyaltyCard (Client, Store, Points) VALUES ('%s', '%s', %d)", client, storeName, points);
                // Execute query
                statement.executeUpdate(sql);
            }
        }
    }

    public static void updateLoyaltyCard(String client, String storeName, Integer points) throws SQLException {
        // Create Connection
        try (Connection connection = DriverManager.getConnection(Database.DB_URL, Database.USER, Database.PASS)) {
            // Create statement
            try (Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
                // Get user with specified username
                String sql = String.format("UPDATE LoyaltyCard SET Points = %d WHERE Client = '%s' AND Store = '%s'", points, client, storeName);
                // Execute query
                statement.executeUpdate(sql);
            }
        }
    }

    public static List<ClientListData> getClientFromStoreName(String storeName) throws SQLException, DatabaseException {
        List<ClientListData> clients = new ArrayList<>();

        // Create Connection
        try (Connection connection = DriverManager.getConnection(Database.DB_URL, Database.USER, Database.PASS)) {
            // Create statement
            try (Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
                // Execute query
                String query = String.format("SELECT * FROM LoyaltyCard WHERE Store = '%s'", storeName);
                ResultSet rs = statement.executeQuery(query);
                while (rs.next()) {
                    String clientUsername = rs.getString("Client");
                    String email = getLEmailFromUsername(clientUsername);
                    int clientPoints = rs.getInt("Points");
                    clients.add(new ClientListData(clientUsername, email, clientPoints));
                }

                rs.close();
            }
        }
        return clients;
    }

    public static String getLEmailFromUsername(String client) throws SQLException, DatabaseException {
        String email;

        // Create Connection
        try (Connection connection = DriverManager.getConnection(Database.DB_URL, Database.USER, Database.PASS)) {
            // Create statement
            try (Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
                // Get user with specified username
                String sql = String.format("SELECT Email FROM User WHERE Username='%s' ", client);
                // Execute query
                ResultSet rs = statement.executeQuery(sql);
                // Empty result
                if (!rs.first())
                    throw new DatabaseException("client");

                rs.first();
                email = rs.getString("Email");

                rs.close();
            }
        }
        return email;
    }
}
