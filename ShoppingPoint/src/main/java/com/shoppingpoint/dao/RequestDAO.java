package com.shoppingpoint.dao;

import com.shoppingpoint.adapter.ProductAdapter;
import com.shoppingpoint.exception.DatabaseException;
import com.shoppingpoint.model.Request;
import com.shoppingpoint.model.product.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RequestDAO {
    private RequestDAO() {
        throw new IllegalStateException();
    }

    public static List<Request> getRequestsOfProduct(int productId) throws SQLException, DatabaseException {
        ArrayList<Request> requests = new ArrayList<>();

        // Create Connection
        try (Connection connection = DriverManager.getConnection(Database.DB_URL, Database.USER, Database.PASS)) {
            // Create statement
            try (Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
                // Execute query
                ResultSet rs = statement.executeQuery(String.format("SELECT * FROM Request WHERE ProductId = %d", productId));
                while (rs.next()) {
                    requests.add(getRequest(rs));
                }

                rs.close();
            }
        }
        return requests;
    }

    public static Request getRequestById(int id) throws SQLException, DatabaseException {
        Request request;
//            Create Connection
        try (Connection connection = DriverManager.getConnection(Database.DB_URL, Database.USER, Database.PASS)) {
//            Create Statement
            try (Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
//            Get item
                String sql = String.format("SELECT * FROM Request WHERE RequestId=%d", id);
//            Execute query
                ResultSet rs = statement.executeQuery(sql);
                if (!rs.first())
                    throw new DatabaseException("request");
                rs.first();
                request = getRequest(rs);
                rs.close();
            }
        }
        return request;

    }

    public static void saveNewRequest(int productId, float maxPrice, int quantity) throws SQLException {
        // Create Connection
        try (Connection connection = DriverManager.getConnection(Database.DB_URL, Database.USER, Database.PASS)) {
            // Create statement
            try (Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
                String sql = String.format(java.util.Locale.US, "INSERT INTO Request (ProductId, MaxPrice, Quantity, Accepted) VALUES (%d, %f, %d, %d)", productId, maxPrice, quantity, 0);
                // Execute query
                statement.executeUpdate(sql);
            }
        }
    }

    public static void acceptRequest(int requestId) throws SQLException {
        // Create Connection
        try (Connection connection = DriverManager.getConnection(Database.DB_URL, Database.USER, Database.PASS)) {
            // Create statement
            try (Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
                String sql = String.format("UPDATE Request SET Accepted = 1 WHERE RequestId = %d", requestId);
                // Execute query
                statement.executeUpdate(sql);
            }
        }
    }

    public static List<Request> getAllRequestsNotAccepted() throws SQLException, DatabaseException {
        ArrayList<Request> requests = new ArrayList<>();

        // Create Connection
        try (Connection connection = DriverManager.getConnection(Database.DB_URL, Database.USER, Database.PASS)) {
            // Create statement
            try (Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
                // Execute query
                ResultSet rs = statement.executeQuery("SELECT * FROM Request WHERE Accepted=0");
                while (rs.next()) {
                    requests.add(getRequest(rs));
                }

                rs.close();
            }
        }
        return requests;
    }

    private static Request getRequest(ResultSet rs) throws SQLException, DatabaseException {
        int id = rs.getInt("RequestId");
        float maxPrice = rs.getFloat("MaxPrice");
        int quantity = rs.getInt("Quantity");
        boolean accepted = rs.getBoolean("Accepted");
        Product product = ProductDAO.getProductById(rs.getInt("ProductId"));
        return new Request(id, new ProductAdapter(product), maxPrice, quantity, accepted);
    }
}
