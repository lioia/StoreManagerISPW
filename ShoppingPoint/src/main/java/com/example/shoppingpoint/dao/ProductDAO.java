package com.example.shoppingpoint.dao;

import com.example.shoppingpoint.model.product.*;
import com.example.shoppingpoint.utils.ComputerType;
import com.example.shoppingpoint.utils.ConsoleType;
import com.example.shoppingpoint.utils.ProductType;
import com.example.shoppingpoint.utils.StatusType;
import javafx.scene.image.Image;

import java.io.InputStream;
import java.sql.*;

public class ProductDAO {
    public ProductDAO() {
        throw new IllegalStateException();
    }

    public static Product getProductById(String id) throws Exception {
        Statement statement = null;
        Connection connection = null;
        Product product = null;

        try {
//            Create Connection
            connection = DriverManager.getConnection(Database.DB_URL, Database.USER, Database.PASS);
//            Create Statement
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
//            Get item
            String sql = String.format("SELECT * from Product WHERE ProductId='%s'", id);
//            Execute query
            ResultSet rs = statement.executeQuery(sql);
            if (!rs.first()) {
                throw new Exception("No product found with id: " + id);
            }
            rs.first();
            String name = rs.getString("Name");
            Number price = rs.getDouble("Price");
            Number discountedPrice = rs.getDouble("DiscountedPrice");
            Integer quantity = rs.getInt("Quantity");
            StatusType status = StatusType.valueOf(rs.getString("Status"));
            ProductType type = ProductType.valueOf(rs.getString("Type"));
//            Load Image
            Blob imageBlob = rs.getBlob("Image");
            InputStream is = imageBlob.getBinaryStream();
            Image image = new Image(is);

            switch (type) {
                case CLOTHES -> {
                    String size = rs.getString("Size");
                    String material = rs.getString("Material");
                    product = new ClothesProduct(name, price, discountedPrice, quantity, status, size, material);
                }
                case SHOES -> {
                    String size = rs.getString("Size");
                    String material = rs.getString("Material");
                    String shoesType = rs.getString("ShoesType");
                    product = new ShoesProduct(name, price, discountedPrice, quantity, status, size, material, shoesType);
                }
                case BOOK -> {
                    String author = rs.getString("Author");
                    String plot = rs.getString("Plot");
                    String genre = rs.getString("Genre");
                    product = new BookProduct(name, price, discountedPrice, quantity, status, author, plot, genre);
                }
                case COMICS -> {
                    String author = rs.getString("Author");
                    String artist = rs.getString("Artist");
                    String plot = rs.getString("Plot");
                    String genre = rs.getString("Genre");
                    Integer volume = rs.getInt("VolumeNumber");
                    product = new ComicsProduct(name, price, discountedPrice, quantity, status, author, artist, plot, genre, volume);
                }
                case VIDEOGAME -> {
                    String plot = rs.getString("Plot");
                    String genre = rs.getString("Genre");
                    ConsoleType consoleType = ConsoleType.valueOf(rs.getString("ConsoleType"));
                    product = new VideoGameProduct(name, price, discountedPrice, quantity, status, plot, genre, consoleType);
                }
                case GAMECONSOLE -> {
                    ConsoleType consoleType = ConsoleType.valueOf(rs.getString("ConsoleType"));
                    boolean digitalOnly = rs.getBoolean("DigitalOnly");
                    product = new GameConsoleProduct(name, price, discountedPrice, quantity, status, consoleType, digitalOnly);
                }
                case COMPUTER -> {
                    ComputerType computerType = ComputerType.valueOf(rs.getString("ComputerType"));
                    Integer ram = rs.getInt("Ram");
                    Integer ssd = rs.getInt("SSD");
                    Integer batterySize = rs.getInt("BatterySize");
                    String cpu = rs.getString("CPU");
                    String gpu = rs.getString("GPU");
                    String brand = rs.getString("Brand");
                    Float displaySize = rs.getFloat("DisplaySize");
                    product = new ComputerProduct(name, price, discountedPrice, quantity, status, computerType, ram, ssd, batterySize, cpu, gpu, brand, displaySize);
                }
                case HOMEAPPLIANCES -> {
                    String energyClass = rs.getString("EnergyClass");
                    String specs = rs.getString("Specs");

                    product = new HomeApplianceProduct(name, price, discountedPrice, quantity, status, energyClass, specs);
                }
                default -> throw new Exception("Unrecognized type: " + type.toString());
            }
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
}
