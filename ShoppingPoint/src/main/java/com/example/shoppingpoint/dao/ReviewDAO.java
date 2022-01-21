package com.example.shoppingpoint.dao;

import com.example.shoppingpoint.model.Review;

import java.sql.*;

public class ReviewDAO {
    private ReviewDAO() {
        throw new IllegalStateException();
    }

    public static Review getReviewFromClientAndProductId(String client, int productId) throws Exception {
        Statement statement = null;
        Connection connection = null;
        Review review;

        try {
            // Create Connection
            connection = DriverManager.getConnection(Database.DB_URL, Database.USER, Database.PASS);
            // Create statement
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            // Get user with specified username
            String sql = String.format("SELECT * FROM Review WHERE Client='%s' AND ProductId = %d", client, productId);
            // Execute query
            ResultSet rs = statement.executeQuery(sql);
            // Empty result
            if (!rs.first()) {
                throw new Exception("No review found");
            }
            rs.first();
            int id = rs.getInt("ReviewId");
            float value = rs.getInt("Value");
            review = new Review(id, value, client, productId);
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
        return review;
    }

    public static void addReview(float value, String client, int productId) throws Exception {
        Statement statement = null;
        Connection connection = null;
        try {
            // Create Connection
            connection = DriverManager.getConnection(Database.DB_URL, Database.USER, Database.PASS);
            // Create statement
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            // Get user with specified username
            String sql = String.format("INSERT INTO Review (Value, Client, ProductId) VALUES (%f, '%s', %d)", value, client, productId);
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

    public static void updateReview(int reviewId, float value, String client, int productId) throws Exception {
        Statement statement = null;
        Connection connection = null;
        try {
            // Create Connection
            connection = DriverManager.getConnection(Database.DB_URL, Database.USER, Database.PASS);
            // Create statement
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            // Get user with specified username
            String sql = String.format("UPDATE Review SET Value = %f WHERE ReviewId = %d AND Client = '%s' AND ProductId = %d", value, reviewId, client, productId);
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
