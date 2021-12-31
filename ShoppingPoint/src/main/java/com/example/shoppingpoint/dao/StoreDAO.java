package com.example.shoppingpoint.dao;

import com.example.shoppingpoint.model.Store;
import com.example.shoppingpoint.model.user.Client;
import com.example.shoppingpoint.model.user.StoreOwner;
import com.example.shoppingpoint.model.user.Supplier;
import com.example.shoppingpoint.model.user.User;
import com.example.shoppingpoint.utils.StoreType;
import com.example.shoppingpoint.utils.UserType;

import java.sql.*;

public class StoreDAO {
    private StoreDAO() {
        throw new IllegalStateException();
    }

    public static Store getStoreByName(String name) throws Exception {
        Statement statement = null;
        Connection connection = null;
        Store store = null;

        try {
            // Create Connection
            connection = DriverManager.getConnection(Database.DB_URL, Database.USER, Database.PASS);
            // Create statement
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            // Get user with specified username
            String sql = String.format("SELECT * FROM Store WHERE Name='%s'", name);
            // Execute query
            ResultSet rs = statement.executeQuery(sql);
            // Empty result
            if (!rs.first()) {
                throw new Exception("No store found with name: " + name);
            }
            rs.first();
            String address = rs.getString("Address");
            Integer pointsInEuro = rs.getInt("PointsInEuro");
            StoreType type = StoreType.valueOf(rs.getString("Type"));
            store = new Store(name, address, pointsInEuro, type);

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
        return store;
    }

    public static void saveStore(String name, String address, Integer pointsInEuro, StoreType type) throws Exception {
        Statement statement = null;
        Connection connection = null;

        try {
            // Create Connection
            connection = DriverManager.getConnection(Database.DB_URL, Database.USER, Database.PASS);
            // Create statement
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            // Get user with specified username
            String sql = String.format("INSERT INTO Store (Name, Address, Type, PointsInEuro) VALUES ('%s', '%s', '%s', %d)", name, address, type, pointsInEuro);
            // Execute query
//          TODO controllo result
            int result = statement.executeUpdate(sql);
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
