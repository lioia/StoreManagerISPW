package com.example.shoppingpoint.dao;

import com.example.shoppingpoint.model.SoldProduct;
import com.example.shoppingpoint.model.product.*;
import com.example.shoppingpoint.utils.StoreType;

import java.sql.*;
import java.time.LocalDate;

public class SoldProductDAO {
    public SoldProductDAO() { throw new IllegalStateException(); }

    public static SoldProduct getSoldProductById(Integer id) throws Exception {
        Statement statement = null;
        Connection connection = null;
        SoldProduct soldProduct = null;

        try {
//            Create Connection
            connection = DriverManager.getConnection(Database.DB_URL, Database.USER, Database.PASS);
//            Create Statement
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
//            Get item
            String sql = String.format("SELECT * FROM SoldProduct WHERE SoldProductId=%s", id);
//            Execute query
            ResultSet rs = statement.executeQuery(sql);
            if (!rs.first()) {
                throw new Exception("No product found with id: " + id);
            }
            rs.first();
            Date sqlDate = rs.getDate("Date");
            LocalDate date = sqlDate.toLocalDate();
            Integer quantity = rs.getInt("Quantity");
            Integer productId = rs.getInt("ProductId");
            Product product = ProductDAO.getProductById(productId);

            soldProduct = new SoldProduct(product, date, quantity);

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

        return soldProduct;
    }

    public static void saveSoldProduct(Integer quantity, LocalDate date, Integer productId) throws Exception {
//        Statement statement = null;
        PreparedStatement statement = null;
        Connection connection = null;

        try {
            // Create Connection
            connection = DriverManager.getConnection(Database.DB_URL, Database.USER, Database.PASS);
            // Create statement
            statement = connection.prepareStatement("INSERT INTO SoldProduct (Quantity, Date, ProductId) VALUES (?, ?, ?)");
            statement.setInt(1, quantity);
            statement.setDate(2, Date.valueOf(date));
            statement.setInt(3, productId);
           // Execute query
//          TODO controllo result
            int result = statement.executeUpdate();
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
