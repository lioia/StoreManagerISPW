package com.example.shoppingpoint.dao;

import com.example.shoppingpoint.model.SoldProduct;
import com.example.shoppingpoint.model.product.*;
import com.example.shoppingpoint.model.user.Client;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SoldProductDAO {
    private SoldProductDAO() {
        throw new IllegalStateException();
    }

    public static List<SoldProduct> getSoldProducts() throws Exception {
        List<SoldProduct> products = new ArrayList<>();

//            Create Connection
        Connection connection = DriverManager.getConnection(Database.DB_URL, Database.USER, Database.PASS);
//            Create Statement
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
//            Execute query
        ResultSet rs = statement.executeQuery("SELECT * FROM SoldProduct");
        while (rs.next()) {
            products.add(getSoldProduct(rs));
        }
        rs.close();
        statement.close();
        connection.close();
        return products;
    }

    public static List<SoldProduct> getProductsOfClient(String client) throws Exception {
        List<SoldProduct> products = new ArrayList<>();

//            Create Connection
        Connection connection = DriverManager.getConnection(Database.DB_URL, Database.USER, Database.PASS);
//            Create Statement
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
//            Get item
        String sql = String.format("SELECT * FROM SoldProduct WHERE Client='%s'", client);
//            Execute query
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {
            products.add(getSoldProduct(rs));
        }
        rs.close();
        statement.close();
        connection.close();
        return products;
    }

    public static void saveSoldProduct(int quantity, LocalDate date, int productId, String clientUsername) throws SQLException {
        // Create Connection
        Connection connection = DriverManager.getConnection(Database.DB_URL, Database.USER, Database.PASS);
        // Create statement
        PreparedStatement statement = connection.prepareStatement("INSERT INTO SoldProduct (Quantity, Date, ProductId, Client) VALUES (?, ?, ?, ?)");
        statement.setInt(1, quantity);
        statement.setDate(2, Date.valueOf(date));
        statement.setInt(3, productId);
        statement.setString(4, clientUsername);
        // Execute query
        statement.executeUpdate();
        statement.close();
        connection.close();
    }

    private static SoldProduct getSoldProduct(ResultSet rs) throws Exception {
        Date sqlDate = rs.getDate("Date");
        LocalDate date = sqlDate.toLocalDate();
        Integer quantity = rs.getInt("Quantity");
        Integer productId = rs.getInt("ProductId");
        String clientUsername = rs.getString("Client");
        Product product = ProductDAO.getProductById(productId);
        Client client = (Client) UserDAO.getUserByUsername(clientUsername);

        return new SoldProduct(client, product, date, quantity);
    }
}
