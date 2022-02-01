package com.example.shoppingpoint.dao;

import com.example.shoppingpoint.model.Review;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReviewDAO {
    private ReviewDAO() {
        throw new IllegalStateException();
    }

    public static List<Review> getReviewsOfProduct(int productId) throws SQLException {
        ArrayList<Review> reviews = new ArrayList<>();

        // Create Connection
        Connection connection = DriverManager.getConnection(Database.DB_URL, Database.USER, Database.PASS);
        // Create statement
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        // Execute query
        ResultSet rs = statement.executeQuery(String.format("SELECT * FROM Review WHERE ProductId = %d", productId));
        while (rs.next()) {
            Review review = getReview(rs);
            reviews.add(review);
        }

        rs.close();
        statement.close();
        connection.close();
        return reviews;
    }

    public static Review getReviewFromClientAndProductId(String client, int productId) throws Exception {
        Review review;

        // Create Connection
        Connection connection = DriverManager.getConnection(Database.DB_URL, Database.USER, Database.PASS);
        // Create statement
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        // Get user with specified username
        String sql = String.format("SELECT * FROM Review WHERE Client='%s' AND ProductId = %d", client, productId);
        // Execute query
        ResultSet rs = statement.executeQuery(sql);
        // Empty result
        if (!rs.first()) // TODO handle exception
            throw new Exception("No review found");

        rs.first();
        review = getReview(rs);
        rs.close();
        statement.close();
        connection.close();
        return review;
    }

    public static void addReview(float value, String client, int productId) throws SQLException {
        // Create Connection
        Connection connection = DriverManager.getConnection(Database.DB_URL, Database.USER, Database.PASS);
        // Create statement
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        String sql = String.format(java.util.Locale.US, "INSERT INTO Review (Value, Client, ProductId) VALUES (%f, '%s', %d)", value, client, productId);
        // Execute query
        statement.executeUpdate(sql);
        statement.close();
        connection.close();
    }

    public static void updateReview(int reviewId, float value, String client, int productId) throws SQLException {
        // Create Connection
        Connection connection = DriverManager.getConnection(Database.DB_URL, Database.USER, Database.PASS);
        // Create statement
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        // Get user with specified username
        String sql = String.format(java.util.Locale.US, "UPDATE Review SET Value = %s WHERE ReviewId = %d AND Client = '%s' AND ProductId = %d", value, reviewId, client, productId);
        // Execute query
        statement.executeUpdate(sql);
        statement.close();
        connection.close();
    }

    private static Review getReview(ResultSet rs) throws SQLException {
        int id = rs.getInt("ReviewId");
        int productId = rs.getInt("ProductId");
        String client = rs.getString("Client");
        float value = rs.getInt("Value");
        return new Review(id, value, client, productId);
    }
}
