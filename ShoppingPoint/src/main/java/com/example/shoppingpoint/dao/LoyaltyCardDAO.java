package com.example.shoppingpoint.dao;

import com.example.shoppingpoint.model.LoyaltyCard;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.example.shoppingpoint.utils.ClientListData;

public class LoyaltyCardDAO {
    private LoyaltyCardDAO() {
        throw new IllegalStateException();
    }

    public static LoyaltyCard getLoyaltyCardFromClientAndStoreName(String client, String storeName) throws Exception {
        Statement statement = null;
        Connection connection = null;
        LoyaltyCard card;

        try {
            // Create Connection
            connection = DriverManager.getConnection(Database.DB_URL, Database.USER, Database.PASS);
            // Create statement
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            // Get user with specified username
            String sql = String.format("SELECT * FROM LoyaltyCard WHERE Client='%s' AND Store = '%s'", client, storeName);
            // Execute query
            ResultSet rs = statement.executeQuery(sql);
            // Empty result
            if (!rs.first()) {
                throw new Exception("No loyalty card found");
            }
            rs.first();
            Integer points = rs.getInt("Points");
            card = new LoyaltyCard(points, client, storeName);
            rs.close();
        } finally {
            // Clean-up dell'ambiente
            try {
                if (statement != null)
                    statement.close();
            } catch (SQLException se2) {
                se2.printStackTrace();
            }
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return card;
    }

    public static void saveLoyaltyCard(String client, String storeName, Integer points) throws Exception {
        Statement statement = null;
        Connection connection = null;

        try {
            // Create Connection
            connection = DriverManager.getConnection(Database.DB_URL, Database.USER, Database.PASS);
            // Create statement
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            // Get user with specified username
            String sql = String.format("INSERT INTO LoyaltyCard (Client, Store, Points) VALUES ('%s', '%s', %d)", client, storeName, points);
            // Execute query
            statement.executeUpdate(sql);
        } finally {
            // Clean-up dell'ambiente
            try {
                if (statement != null)
                    statement.close();
            } catch (SQLException se2) {
                se2.printStackTrace();
            }
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    public static void updateLoyaltyCard(String client, String storeName, Integer points) throws Exception {
        Statement statement = null;
        Connection connection = null;
        try {
            // Create Connection
            connection = DriverManager.getConnection(Database.DB_URL, Database.USER, Database.PASS);
            // Create statement
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            // Get user with specified username
            String sql = String.format("UPDATE LoyaltyCard SET Points = %d WHERE Client = '%s' AND Store = '%s'", points, client, storeName);
            // Execute query
            statement.executeUpdate(sql);
        } finally {
            // Clean-up dell'ambiente
            try {
                if (statement != null)
                    statement.close();
            } catch (SQLException se2) {
                se2.printStackTrace();
            }
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    public static List<ClientListData> getClientFromStoreName (String storeName) throws Exception{
        Statement statement = null;
        Connection connection = null;
        List<ClientListData> clients = new ArrayList<>();

        try {
            // Create Connection
            connection = DriverManager.getConnection(Database.DB_URL, Database.USER, Database.PASS);
            // Create statement
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            // Execute query
            String query = String.format("SELECT * FROM LoyaltyCard WHERE Store = '%s'", storeName);
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                String clientUsername=rs.getString("Client");
                String email = getLEmailFromUsername(clientUsername);
                Integer clientPoints=rs.getInt("Points");
                clients.add(new ClientListData(clientUsername,email,clientPoints));
            }

            rs.close();
        } finally {
            // Clean-up dell'ambiente
            try {
                if (statement != null)
                    statement.close();
            } catch (SQLException se2) {
                se2.printStackTrace();
            }
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return clients;
    }

    public static String getLEmailFromUsername(String client) throws Exception {
        Statement statement = null;
        Connection connection = null;
        String email;

        try {
            // Create Connection
            connection = DriverManager.getConnection(Database.DB_URL, Database.USER, Database.PASS);
            // Create statement
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            // Get user with specified username
            String sql = String.format("SELECT Email FROM User WHERE Username='%s' ", client);
            // Execute query
            ResultSet rs = statement.executeQuery(sql);
            // Empty result
            if (!rs.first()) {
                throw new Exception("No client found");
            }
            rs.first();
            email  = rs.getString("Email");

            rs.close();
        } finally {
            // Clean-up dell'ambiente
            try {
                if (statement != null)
                    statement.close();
            } catch (SQLException se2) {
                se2.printStackTrace();
            }
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return email;
    }


}
