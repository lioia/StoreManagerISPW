package com.example.shoppingpoint.dao;

import java.sql.*;

public class RequestDAO {
    private RequestDAO() {
        throw new IllegalStateException();
    }

    public static void saveNewRequest(int productId, float maxPrice, int quantity) throws Exception {
        Statement statement = null;
        Connection connection = null;

        try {
            // Create Connection
            connection = DriverManager.getConnection(Database.DB_URL, Database.USER, Database.PASS);
            // Create statement
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = String.format("INSERT INTO Request (ProductId, MaxPrice, Quantity, Accepted) VALUES (%d, %f, %d, %d)", productId, maxPrice, quantity, 0);
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
