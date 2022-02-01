package com.example.shoppingpoint.dao;

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
        Connection connection = DriverManager.getConnection(Database.DB_URL, Database.USER, Database.PASS);
        // Create statement
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        String sql = String.format(java.util.Locale.US, "INSERT INTO Offer (Supplier,RequestId,OfferPrice) VALUES ('%s', %d, %f)", supplierUsername, requestId, offerPrice);
        // Execute query
        statement.executeUpdate(sql);
    }


    public static Offer getAcceptedOfferOfRequest(int requestId) throws Exception {
        Offer offer;

        // Create Connection
        Connection connection = DriverManager.getConnection(Database.DB_URL, Database.USER, Database.PASS);
        // Create statement
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        // Execute query
        ResultSet rs = statement.executeQuery(String.format("SELECT * FROM Offer WHERE RequestId = %d AND Accepted = 1", requestId));
        if (!rs.first()) // TODO handle exception
            throw new Exception("No accepted offer found");
        rs.first();
        offer = getOffer(rs);
        rs.close();
        statement.close();
        connection.close();
        return offer;
    }

    public static List<Offer> getOffersOfRequest(int requestId) throws SQLException {
        ArrayList<Offer> offers = new ArrayList<>();

        // Create Connection
        Connection connection = DriverManager.getConnection(Database.DB_URL, Database.USER, Database.PASS);
        // Create statement
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        // Execute query
        ResultSet rs = statement.executeQuery(String.format("SELECT * FROM Offer WHERE RequestId = %d", requestId));
        while (rs.next()) {
            offers.add(getOffer(rs));
        }
        rs.close();
        statement.close();
        connection.close();
        return offers;
    }

    public static void acceptOffer(int offerId) throws SQLException {
        // Create Connection
        Connection connection = DriverManager.getConnection(Database.DB_URL, Database.USER, Database.PASS);
        // Create statement
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        String sql = String.format("UPDATE Offer SET Accepted = 1 AND Checked = 1 WHERE RequestId = %d", offerId);
        // Execute query
        statement.executeUpdate(sql);
        statement.close();
        connection.close();
    }
    public static void checkedOffer(String supplier) throws SQLException {
        // Create Connection
        Connection connection = DriverManager.getConnection(Database.DB_URL, Database.USER, Database.PASS);
        // Create statement
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        String sql = String.format("UPDATE Offer SET Checked = 0 WHERE Supplier = '%s' AND Checked = 1", supplier);
        // Execute query
        statement.executeUpdate(sql);
        statement.close();
        connection.close();
    }

    private static Offer getOffer(ResultSet rs) throws SQLException {
        int offerId = rs.getInt("OfferId");
        int requestId = rs.getInt("RequestId");
        float offerPrice = rs.getFloat("OfferPrice");
        boolean accepted = rs.getBoolean("Accepted");
        String supplierUsername = rs.getString("Supplier");
        return new Offer(offerId, requestId, offerPrice, accepted, supplierUsername);
    }

    public static int countAcceptedOffer(String supplier)throws SQLException{
        // Create Connection
        Connection connection = DriverManager.getConnection(Database.DB_URL, Database.USER, Database.PASS);
        // Create statement
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        // Execute query
        ResultSet rs = statement.executeQuery(String.format("SELECT COUNT(*) AS total FROM Offer WHERE Supplier = '%s' and Checked=1", supplier));

        rs.next();
        int n=rs.getInt("total");
        rs.close();
        statement.close();
        connection.close();
        return n;


    }

}
