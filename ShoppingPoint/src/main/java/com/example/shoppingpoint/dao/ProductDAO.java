package com.example.shoppingpoint.dao;

import com.example.shoppingpoint.factory.ProductFactory;
import com.example.shoppingpoint.model.product.*;
import com.example.shoppingpoint.utils.*;
import javafx.scene.image.Image;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ProductDAO {
    public ProductDAO() {
        throw new IllegalStateException();
    }

    public static List<Product> getProductsFromStore(String storeName) throws Exception {
        Statement statement = null;
        Connection connection = null;
        List<Product> products = new ArrayList<>();

        try {
            // Create Connection
            connection = DriverManager.getConnection(Database.DB_URL, Database.USER, Database.PASS);
            // Create statement
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            // Execute query
            String query = String.format("SELECT * FROM Product WHERE Store = '%s'", storeName);
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                products.add(getProduct(rs));
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
        return products;
    }

    public static Product getProductById(Integer id) throws Exception {
        Statement statement = null;
        Connection connection = null;
        Product product;

        try {
//            Create Connection
            connection = DriverManager.getConnection(Database.DB_URL, Database.USER, Database.PASS);
//            Create Statement
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
//            Get item
            String sql = String.format("SELECT * FROM Product WHERE ProductId=%d", id);
//            Execute query
            ResultSet rs = statement.executeQuery(sql);
            if (!rs.first()) {
                throw new Exception("No product found with id: " + id);
            }
            rs.first();
            product = getProduct(rs);
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
        return product;
    }

    private static Product getProduct(ResultSet rs) throws Exception {
        ProductFactory factory = new ProductFactory();
        int id = rs.getInt("ProductId");
        String name = rs.getString("Name");
        float price = rs.getFloat("Price");
        float discountedPrice = rs.getFloat("DiscountedPrice");
        int quantity = rs.getInt("Quantity");
        String status = rs.getString("Status");
        String storeName = rs.getString("Store");
        ProductType type = ProductType.valueOf(rs.getString("Type"));
        Image image = null;
//            Load Image
        Blob imageBlob = rs.getBlob("Image");
        if (!rs.wasNull()) { // Image was not null
            InputStream is = imageBlob.getBinaryStream();
            image = new Image(is);
        }

        return factory.createProduct(type, id, name, price, discountedPrice, quantity, status, image, storeName,
                rs.getString("Size"), rs.getString("Material"), rs.getString("ShoesType"),
                rs.getString("Author"), rs.getString("Artist"), rs.getString("Plot"), rs.getString("Genre"), rs.getInt("VolumeNumber"),
                rs.getString("ConsoleType"), rs.getBoolean("DigitalOnly"),
                rs.getString("ComputerType"), rs.getInt("Ram"), rs.getInt("SSD"), rs.getString("CPU"),
                rs.getString("GPU"), rs.getInt("BatterySize"), rs.getFloat("DisplaySize"),
                rs.getString("Brand"), rs.getString("EnergyClass"), rs.getString("Specs"));
    }

    public static void saveClothesProduct(String name, Float price, Float discountedPrice, Integer quantity, String status, String storeName, String size, String material) throws Exception {
        String sql = String.format(Locale.US, "INSERT INTO Product (Name, Price, DiscountedPrice, Quantity, Type, Status, Store, Size, Material) VALUES ('%s', %f, %f, %d, '%s', '%s', '%s', '%s', '%s')", name, price, discountedPrice, quantity, "CLOTHES", status, storeName, size, material);
        saveProduct(sql);
    }

    public static void saveShoesProduct(String name, Float price, Float discountedPrice, Integer quantity, String status, String storeName, String size, String material, String shoesType) throws Exception {
        String sql = String.format(Locale.US, "INSERT INTO Product (Name, Price, DiscountedPrice, Quantity, Type, Status, Store, Size, Material, ShoesType) VALUES ('%s', %f, %f, %d, '%s', '%s', '%s', '%s', '%s', '%s')", name, price, discountedPrice, quantity, "SHOES", status, storeName, size, material, shoesType);
        saveProduct(sql);
    }

    public static void saveBookProduct(String name, Float price, Float discountedPrice, Integer quantity, String status, String storeName, String author, String plot, String genre) throws Exception {
        String sql = String.format(Locale.US, "INSERT INTO Product (Name, Price, DiscountedPrice, Quantity, Type, Status, Store, Author, Plot, Genre) VALUES ('%s', %f, %f, %d, '%s', '%s', '%s', '%s', '%s', '%s')", name, price, discountedPrice, quantity, "BOOK", status, storeName, author, plot, genre);
        saveProduct(sql);
    }

    public static void saveComicsProduct(String name, Float price, Float discountedPrice, Integer quantity, String status, String storeName, String author, String artist, String plot, String genre, Integer volume) throws Exception {
        String sql = String.format(Locale.US, "INSERT INTO Product (Name, Price, DiscountedPrice, Quantity, Type, Status, Store, Author, Artist, Plot, Genre, VolumeNumber) VALUES ('%s', %f, %f, %d, '%s', '%s', '%s', '%s', '%s', '%s', '%s', %d)", name, price, discountedPrice, quantity, "COMICS", status, storeName, author, artist, plot, genre, volume);
        saveProduct(sql);
    }

    public static void saveVideoGameProduct(String name, Float price, Float discountedPrice, Integer quantity, String status, String storeName, String plot, String genre, String consoleType) throws Exception {
        String sql = String.format(Locale.US, "INSERT INTO Product (Name, Price, DiscountedPrice, Quantity, Type, Status, Store, Plot, Genre, ConsoleType) VALUES ('%s', %f, %f, %d, '%s', '%s', '%s', '%s', '%s', '%s')", name, price, discountedPrice, quantity, "VIDEOGAME", status, storeName, plot, genre, consoleType);
        saveProduct(sql);
    }

    public static void saveGameConsoleProduct(String name, Float price, Float discountedPrice, Integer quantity, String status, String storeName, String consoleType, boolean digitalOnly) throws Exception {
        String sql = String.format(Locale.US, "INSERT INTO Product (Name, Price, DiscountedPrice, Quantity, Type, Status, Store, ConsoleType, DigitalOnly) VALUES ('%s', %f, %f, %d, '%s', '%s', '%s', '%s', %d)", name, price, discountedPrice, quantity, "GAMECONSOLE", status, storeName, consoleType, digitalOnly ? 1 : 0);
        saveProduct(sql);
    }

    public static void saveComputerProduct(String name, Float price, Float discountedPrice, Integer quantity, String status, String storeName, String computerType, Integer ram, Integer ssd, Integer batterySize, String cpu, String gpu, String brand, Float displaySize) throws Exception {
        String sql = String.format(Locale.US, "INSERT INTO Product (Name, Price, DiscountedPrice, Quantity, Type, Status, Store, ComputerType, Ram, SSD, BatterySize, CPU, GPU, Brand, DisplaySize) VALUES ('%s', %f, %f, %d, '%s', '%s', '%s', '%s', %d, %d, %d, '%s', '%s', '%s', %f)", name, price, discountedPrice, quantity, "COMPUTER", status, storeName, computerType, ram, ssd, batterySize, cpu, gpu, brand, displaySize);
        saveProduct(sql);
    }

    public static void saveHomeApplianceProduct(String name, Float price, Float discountedPrice, Integer quantity, String status, String storeName, String energyClass, String specs) throws Exception {
        String sql = String.format(Locale.US, "INSERT INTO Product (Name, Price, DiscountedPrice, Quantity, Type, Status, Store, EnergyClass, Specs) VALUES ('%s', %f, %f, %d, '%s', '%s', '%s', '%s', '%s')", name, price, discountedPrice, quantity, "HOMEAPPLIANCES", status, storeName, energyClass, specs);
        saveProduct(sql);
    }

    private static void saveProduct(String sql) throws Exception {
        Statement statement = null;
        Connection connection = null;

        try {
            // Create Connection
            connection = DriverManager.getConnection(Database.DB_URL, Database.USER, Database.PASS);
            // Create statement
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
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

    public static void updateProductQuantity(Integer productId, Integer quantity) throws Exception {
        Statement statement = null;
        Connection connection = null;
        try {
            // Create Connection
            connection = DriverManager.getConnection(Database.DB_URL, Database.USER, Database.PASS);
            // Create statement
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            // Get user with specified username
            String sql = String.format("UPDATE Product SET Quantity = %d WHERE ProductId =  %d", quantity, productId);
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

    public static void updateProduct(int productId, float price, float discountedPrice, int quantity) throws Exception {
        Statement statement = null;
        Connection connection = null;
        try {
            // Create Connection
            connection = DriverManager.getConnection(Database.DB_URL, Database.USER, Database.PASS);
            // Create statement
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = String.format("UPDATE Product SET Price = %f, DiscountedPrice = %f, Quantity = %d WHERE ProductId =  %d", price, discountedPrice, quantity, productId);
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

    public static void setImageOfProductId(int productId, InputStream stream) throws SQLException {
        PreparedStatement statement = null;
        Connection connection = null;
        try {
            // Create Connection
            connection = DriverManager.getConnection(Database.DB_URL, Database.USER, Database.PASS);
            // Create statement
            statement = connection.prepareStatement("UPDATE Product SET Image = ?WHERE ProductId = ?");
            statement.setBinaryStream(1, stream);
            statement.setInt(2, productId);
            // Execute query
            statement.executeUpdate();
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
