package com.example.shoppingpoint.dao;

import com.example.shoppingpoint.model.user.Client;
import com.example.shoppingpoint.model.user.StoreOwner;
import com.example.shoppingpoint.model.user.Supplier;
import com.example.shoppingpoint.model.user.User;
import com.example.shoppingpoint.utils.UserType;

import java.sql.*;

// TODO gestione eccezioni
public class UserDAO {
    private UserDAO() {
        throw new IllegalStateException();
    }

    public static User getUserByUsernameAndPasssword(String username, String password) throws Exception {
        Statement statement = null;
        Connection connection = null;
        User user = null;

        try {
            // Create Connection
            connection = DriverManager.getConnection(Database.DB_URL, Database.USER, Database.PASS);
            // Create statement
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            // Get user with specified username
            String sql = String.format("SELECT * FROM User WHERE Username='%s' AND Password='%s'", username, password);
            // Execute query
            ResultSet rs = statement.executeQuery(sql);
            // Empty result
            if (!rs.first()) {
                throw new Exception("No user found with username: " + username);
            }
            rs.first();
            String email = rs.getString("Email");
            UserType type = UserType.valueOf(rs.getString("Type"));

            user = switch (type) {

                case STOREOWNER -> new StoreOwner(username, email, password);
                case SUPPLIER -> new Supplier(username, email, password);
                default -> new Client(username, email, password);
            };
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
        return user;
    }

    public static void saveUser(String username, String email, String password, UserType userType) throws Exception {
        Statement statement = null;
        Connection connection = null;

        try {
            // Create Connection
            connection = DriverManager.getConnection(Database.DB_URL, Database.USER, Database.PASS);
            // Create statement
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            // Get user with specified username
            String sql = String.format("INSERT INTO User (Username, Email, Password, Type) VALUES ('%s', '%s', '%s', '%s')", username, email, password, userType);
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
