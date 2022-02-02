package com.example.shoppingpoint.dao;

import com.example.shoppingpoint.exception.DatabaseException;
import com.example.shoppingpoint.model.user.Client;
import com.example.shoppingpoint.model.user.StoreOwner;
import com.example.shoppingpoint.model.user.Supplier;
import com.example.shoppingpoint.model.user.User;
import com.example.shoppingpoint.utils.UserType;

import java.sql.*;

public class UserDAO {
    private UserDAO() {
        throw new IllegalStateException();
    }

    public static String getEmailByUsername(String username) throws SQLException, DatabaseException {
        // Create Connection
        Connection connection = DriverManager.getConnection(Database.DB_URL, Database.USER, Database.PASS);
        // Create statement
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        // Get user with specified username
        String sql = String.format("SELECT Email FROM User WHERE Username='%s'", username);
        // Execute query
        ResultSet rs = statement.executeQuery(sql);
        // Empty result
        if (!rs.first())
            throw new DatabaseException("email");

        rs.first();
        String email = rs.getString("Email");

        rs.close();
        statement.close();
        connection.close();
        return email;
    }

    public static User getUserByUsername(String username) throws SQLException, DatabaseException {
        // Create Connection
        Connection connection = DriverManager.getConnection(Database.DB_URL, Database.USER, Database.PASS);
        // Create statement
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        // Get user with specified username
        String sql = String.format("SELECT * FROM User WHERE Username='%s'", username);
        // Execute query
        ResultSet rs = statement.executeQuery(sql);
        // Empty result
        if (!rs.first())
            throw new DatabaseException("username");

        rs.first();
        String password = rs.getString("Password");
        String email = rs.getString("Email");
        UserType type = UserType.valueOf(rs.getString("Type"));

        User user = switch (type) {
            case STOREOWNER -> new StoreOwner(username, email, password);
            case SUPPLIER -> new Supplier(username, email, password);
            default -> new Client(username, email, password); // Client
        };
        rs.close();
        statement.close();
        connection.close();
        return user;
    }

    public static void saveUser(String username, String email, String password, UserType userType) throws SQLException {
        // Create Connection
        Connection connection = DriverManager.getConnection(Database.DB_URL, Database.USER, Database.PASS);
        // Create statement
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        // Get user with specified username
        String sql = String.format("INSERT INTO User (Username, Email, Password, Type) VALUES ('%s', '%s', '%s', '%s')", username, email, password, userType);
        // Execute query
        statement.executeUpdate(sql);
        statement.close();
        connection.close();
    }
}
