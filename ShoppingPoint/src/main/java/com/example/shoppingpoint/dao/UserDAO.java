package com.example.shoppingpoint.dao;

import com.example.shoppingpoint.bean.RegisterBean;
import com.example.shoppingpoint.model.user.Client;
import com.example.shoppingpoint.model.user.User;
import com.example.shoppingpoint.utils.UserType;

import java.sql.*;

// TODO gestione eccezioni
public class UserDAO {
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
                Exception e = new Exception("No user found with username: " + username);
                throw e;
            }
            rs.first();
            String email = rs.getString("Email");
            UserType type = UserType.valueOf(rs.getString("Type"));

            switch (type) {
                case Client:
                    user = new Client(username, email, password);
                    break;
//                    TODO altre classi
                case StoreOwner:
                    user= new Client(username, email, password);
                    break;
                case Supplier:
                    user = new Client(username, email, password);
                    break;
            }
            rs.close();
        } finally {
            // Clean-up dell'ambiente
            try {
                if (statement != null)
                    statement.close();
            } catch (SQLException se2) {
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
            int result = statement.executeUpdate(sql);
//            TODO controllo result
        } finally {
            // Clean-up dell'ambiente
            try {
                if (statement != null)
                    statement.close();
            } catch (SQLException se2) {
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
