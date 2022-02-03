package com.example.shoppingpoint.dao;

import com.example.shoppingpoint.exception.DatabaseException;
import com.example.shoppingpoint.model.Offer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OfferDAO {
    private OfferDAO() {
        throw new IllegalStateException();
    }

    public static void saveOffer(String supplierUsername, int requestId, float offerPrice) throws SQLException {
        // Create Connection
        try (Connection connection = DriverManager.getConnection(Database.DB_URL, Database.USER, Database.PASS)) {
            // Create statement
            try (Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
                String sql = String.format(java.util.Locale.US, "INSERT INTO Offer (Supplier,RequestId,OfferPrice) VALUES ('%s', %d, %f)", supplierUsername, requestId, offerPrice);
                // Execute query
                statement.executeUpdate(sql);
            }
        }
    }

    public static Offer getAcceptedOfferOfRequest(int requestId) throws SQLException, DatabaseException {
        Offer offer;

        // Create Connection
        try (Connection connection = DriverManager.getConnection(Database.DB_URL, Database.USER, Database.PASS)) {
            // Create statement
            try (Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
                // Execute query
                ResultSet rs = statement.executeQuery(String.format("SELECT * FROM Offer WHERE RequestId = %d AND Accepted = 1", requestId));
                if (!rs.first())
                    throw new DatabaseException("accepted offer");
                rs.first();
                offer = getOffer(rs);
                rs.close();
            }
        }
        return offer;
    }

    public static List<Offer> getAcceptedOffersOfSupplier(String supplier) throws SQLException {
        ArrayList<Offer> offers = new ArrayList<>();

        // Create Connection
        try (Connection connection = DriverManager.getConnection(Database.DB_URL, Database.USER, Database.PASS)) {
            // Create statement
            try (Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
                // Execute query
                ResultSet rs = statement.executeQuery(String.format("SELECT * FROM Offer WHERE Supplier = '%s' AND Accepted=1", supplier));
                while (rs.next()) {
                    offers.add(getOffer(rs));
                }
                rs.close();
            }
        }
        return offers;
    }

    public static List<Offer> getOffersOfRequest(int requestId) throws SQLException {
        ArrayList<Offer> offers = new ArrayList<>();

        // Create Connection
        try (Connection connection = DriverManager.getConnection(Database.DB_URL, Database.USER, Database.PASS)) {
            // Create statement
            try (Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
                // Execute query
                ResultSet rs = statement.executeQuery(String.format("SELECT * FROM Offer WHERE RequestId = %d", requestId));
                while (rs.next()) {
                    offers.add(getOffer(rs));
                }
                rs.close();
            }
        }
        return offers;
    }

    public static void acceptOffer(int offerId) throws SQLException {
        // Create Connection
        try (Connection connection = DriverManager.getConnection(Database.DB_URL, Database.USER, Database.PASS)) {
            // Create statement
            try (Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
                String sql = String.format("UPDATE Offer SET Accepted = 1 , Checked = 1 WHERE OfferId = %d", offerId);
                // Execute query
                statement.executeUpdate(sql);
            }
        }
    }

    public static void checkedOffer(String supplier) throws SQLException {
        // Create Connection
        try (Connection connection = DriverManager.getConnection(Database.DB_URL, Database.USER, Database.PASS)) {
            // Create statement
            try (Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
                String sql = String.format("UPDATE Offer SET Checked = 0 WHERE Supplier = '%s' AND Checked = 1", supplier);
                // Execute query
                statement.executeUpdate(sql);
            }
        }
    }

    public static Boolean isOfferChecked(int offerId) throws SQLException{
        Boolean checkedOffer;
        // Create Connection
        try (Connection connection = DriverManager.getConnection(Database.DB_URL, Database.USER, Database.PASS)) {
            // Create statement
            try (Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
                // Execute query
                ResultSet rs = statement.executeQuery(String.format("SELECT * FROM Offer WHERE offerId = %d and checked=0", offerId));
                checkedOffer= rs.first();
                rs.close();
            }
        }
        return checkedOffer;
    }

    private static Offer getOffer(ResultSet rs) throws SQLException {
        int offerId = rs.getInt("OfferId");
        int requestId = rs.getInt("RequestId");
        float offerPrice = rs.getFloat("OfferPrice");
        boolean accepted = rs.getBoolean("Accepted");
        String supplierUsername = rs.getString("Supplier");
        return new Offer(offerId, requestId, offerPrice, accepted, supplierUsername);
    }

    public static int countAcceptedOffer(String supplier) throws SQLException {
        // Create Connection
        int n;
        try (Connection connection = DriverManager.getConnection(Database.DB_URL, Database.USER, Database.PASS)) {
            // Create statement
            try (Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
                // Execute query
                ResultSet rs = statement.executeQuery(String.format("SELECT COUNT(*) AS total FROM Offer WHERE Supplier = '%s' and Checked=1", supplier));

                rs.next();
                n = rs.getInt("total");
                rs.close();
            }
        }
        return n;
    }
    
}
