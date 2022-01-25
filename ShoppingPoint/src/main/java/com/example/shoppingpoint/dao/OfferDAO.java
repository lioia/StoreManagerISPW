package com.example.shoppingpoint.dao;

import com.example.shoppingpoint.model.Offer;
import com.example.shoppingpoint.model.Request;
import com.example.shoppingpoint.model.user.Supplier;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OfferDAO {
    private OfferDAO() {
        throw new IllegalStateException();
    }

    public static Offer getAcceptedOfferOfRequest(int requestId) throws Exception {
        Statement statement = null;
        Connection connection = null;
        Offer offer;

        try {
            // Create Connection
            connection = DriverManager.getConnection(Database.DB_URL, Database.USER, Database.PASS);
            // Create statement
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            // Execute query
            ResultSet rs = statement.executeQuery(String.format("SELECT * FROM Offer WHERE RequestId = %d AND Accepted = 1", requestId));
            if (!rs.first())
                throw new Exception("No accepted offer found");
            rs.first();
            offer = getOffer(rs);
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
        return offer;
    }

    public static List<Offer> getOffersOfRequest(int requestId) throws Exception {
        Statement statement = null;
        Connection connection = null;
        ArrayList<Offer> offers = new ArrayList<>();

        try {
            // Create Connection
            connection = DriverManager.getConnection(Database.DB_URL, Database.USER, Database.PASS);
            // Create statement
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            // Execute query
            ResultSet rs = statement.executeQuery(String.format("SELECT * FROM Offer WHERE RequestId = %d", requestId));
            while (rs.next()) {
                offers.add(getOffer(rs));
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
        return offers;
    }

    public static void acceptOffer(int offerId) throws Exception {
        Statement statement = null;
        Connection connection = null;

        try {
            // Create Connection
            connection = DriverManager.getConnection(Database.DB_URL, Database.USER, Database.PASS);
            // Create statement
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = String.format("UPDATE Offer SET Accepted = 1 WHERE RequestId = %d", offerId);
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

    private static Offer getOffer(ResultSet rs) throws Exception {
        int offerId = rs.getInt("OfferId");
        int requestId = rs.getInt("RequestId");
        float offerPrice = rs.getFloat("OfferPrice");
        boolean accepted = rs.getBoolean("Accepted");
        Supplier supplier = (Supplier) UserDAO.getUserByUsername(rs.getString("Supplier"));
        return new Offer(offerId, requestId, offerPrice, accepted, supplier);
    }
}
