package com.example.shoppingpoint.dao;

import com.example.shoppingpoint.exception.DatabaseException;
import com.example.shoppingpoint.model.SoldProduct;
import com.example.shoppingpoint.model.product.Product;
import com.example.shoppingpoint.model.user.Client;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SoldProductDAO {
    private SoldProductDAO() {
        throw new IllegalStateException();
    }

    public static List<SoldProduct> getSoldProducts(String storeName) throws SQLException, DatabaseException {
        List<SoldProduct> products = new ArrayList<>();

//            Create Connection
        try (Connection connection = DriverManager.getConnection(Database.DB_URL, Database.USER, Database.PASS)) {
//            Create Statement
            try (Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
//            Execute query
                ResultSet rs = statement.executeQuery(String.format("SELECT * FROM SoldProduct WHERE Store = '%s'", storeName));
                while (rs.next()) {
                    products.add(getSoldProduct(rs));
                }
                rs.close();
            }
        }
        return products;
    }

    public static List<SoldProduct> getProductsOfClient(String client) throws SQLException, DatabaseException {
        List<SoldProduct> products = new ArrayList<>();

//            Create Connection
        try (Connection connection = DriverManager.getConnection(Database.DB_URL, Database.USER, Database.PASS)) {
//            Create Statement
            try (Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
//            Get item
                String sql = String.format("SELECT * FROM SoldProduct WHERE Client='%s'", client);
//            Execute query
                ResultSet rs = statement.executeQuery(sql);
                while (rs.next()) {
                    products.add(getSoldProduct(rs));
                }
                rs.close();
            }
        }
        return products;
    }

    public static int saveSoldProduct(int quantity, LocalDate date, int productId, String clientUsername, String storeName) throws SQLException, DatabaseException {
        // Create Connection
        try (Connection connection = DriverManager.getConnection(Database.DB_URL, Database.USER, Database.PASS)) {
            // Create statement
            try (PreparedStatement statement = connection.prepareStatement("INSERT INTO SoldProduct (Quantity, Date, ProductId, Client, Store) VALUES (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
                statement.setInt(1, quantity);
                statement.setDate(2, Date.valueOf(date));
                statement.setInt(3, productId);
                statement.setString(4, clientUsername);
                statement.setString(5, storeName);
                // Execute query
                int affectedRows = statement.executeUpdate();
                if (affectedRows == 0) throw new DatabaseException("Could not insert");
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) return generatedKeys.getInt(1);
                    else throw new DatabaseException("Could not insert");
                }
            }
        }
    }

    private static SoldProduct getSoldProduct(ResultSet rs) throws SQLException, DatabaseException {
        Date sqlDate = rs.getDate("Date");
        LocalDate date = sqlDate.toLocalDate();
        Integer quantity = rs.getInt("Quantity");
        Integer productId = rs.getInt("ProductId");
        String clientUsername = rs.getString("Client");

        int soldProductId = rs.getInt("SoldProductId");


        Product product = ProductDAO.getProductById(productId);
        Client client = (Client) UserDAO.getUserByUsername(clientUsername);

        return new SoldProduct(client, product, date, quantity, soldProductId);
    }

    public static SoldProduct getSoldProductById(int soldProductId) throws SQLException, DatabaseException {
        SoldProduct soldProduct = null;
//            Create Connection
        try (Connection connection = DriverManager.getConnection(Database.DB_URL, Database.USER, Database.PASS)) {
//            Create Statement
            try (Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
//            Get item
                String sql = String.format("SELECT * FROM SoldProduct WHERE SoldProductId=%d", soldProductId);
//            Execute query
                ResultSet rs = statement.executeQuery(sql);
                if (!rs.first()) {
                    throw new DatabaseException("Sold product");
                }
                soldProduct = getSoldProduct(rs);
                rs.close();
            }
        }
        return soldProduct;
    }
}
