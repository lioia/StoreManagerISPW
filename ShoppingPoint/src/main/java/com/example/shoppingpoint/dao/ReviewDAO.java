package com.example.shoppingpoint.dao;

import com.example.shoppingpoint.model.Review;
import com.example.shoppingpoint.model.Store;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReviewDAO {
    private ReviewDAO() {
        throw new IllegalStateException();
    }

    public static List<Review> getReviewsOfProduct(int productId) throws Exception {
        Statement statement = null;
        Connection connection = null;
        ArrayList<Review> reviews = new ArrayList<>();

        try {
            // Create Connection
            connection = DriverManager.getConnection(Database.DB_URL, Database.USER, Database.PASS);
            // Create statement
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            // Execute query
            ResultSet rs = statement.executeQuery(String.format("SELECT * FROM Review WHERE ProductId = %d", productId));
            while (rs.next()) {
                Review review = getReview(rs);
                reviews.add(review);
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
        return reviews;
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
            review = getReview(rs);
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
            String valueS =String.format(java.util.Locale.US,"%.2f", value);
            String sql = String.format("INSERT INTO Review (Value, Client, ProductId) VALUES (%s, '%s', %d)", valueS, client, productId);
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
            String valueS =String.format(java.util.Locale.US,"%.2f", value);
            String sql = String.format("UPDATE Review SET Value = %s WHERE ReviewId = %d AND Client = '%s' AND ProductId = %d", valueS, reviewId, client, productId);
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

    private static Review getReview(ResultSet rs)  throws Exception {
        int id = rs.getInt("ReviewId");
        int productId = rs.getInt("ProductId");
        String client = rs.getString("Client");
        float value = rs.getInt("Value");
        return new Review(id, value, client, productId);
    }
}
