package com.example.shoppingpoint.dao;

import com.example.shoppingpoint.model.Request;
import com.example.shoppingpoint.model.Store;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RequestDAO {
    private RequestDAO() {
        throw new IllegalStateException();
    }

    public static List<Request> getRequestsOfProduct(int productId) throws Exception {
        Statement statement = null;
        Connection connection = null;
        ArrayList<Request> requests = new ArrayList<>();

        try {
            // Create Connection
            connection = DriverManager.getConnection(Database.DB_URL, Database.USER, Database.PASS);
            // Create statement
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            // Execute query
            ResultSet rs = statement.executeQuery(String.format("SELECT * FROM Request WHERE ProductId = %d", productId));
            while (rs.next()) {
                int requestId = rs.getInt("RequestId");
                float maxPrice = rs.getFloat("MaxPrice");
                int quantity = rs.getInt("Quantity");
                boolean accepted = rs.getBoolean("Accepted");
                requests.add(new Request(requestId, productId, maxPrice, quantity, accepted));
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
        return requests;
    }

    public static void saveNewRequest(int productId, float maxPrice, int quantity) throws Exception {
        Statement statement = null;
        Connection connection = null;


        try {
            // Create Connection
            connection = DriverManager.getConnection(Database.DB_URL, Database.USER, Database.PASS);
            // Create statement
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String maxPriceS=String.format(java.util.Locale.US,"%.2f", maxPrice);
            String sql = String.format("INSERT INTO Request (ProductId, MaxPrice, Quantity, Accepted) VALUES (%d, %s, %d, %d)", productId, maxPriceS, quantity, 0);
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

    public static void acceptRequest(int requestId) throws Exception {
        Statement statement = null;
        Connection connection = null;

        try {
            // Create Connection
            connection = DriverManager.getConnection(Database.DB_URL, Database.USER, Database.PASS);
            // Create statement
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = String.format("UPDATE Request SET Accepted = 1 WHERE RequestId = %d", requestId);
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
}
