package com.example.shoppingpoint.dao;

import com.example.shoppingpoint.exception.DatabaseException;
import com.example.shoppingpoint.factory.ProductFactory;
import com.example.shoppingpoint.model.product.*;
import com.example.shoppingpoint.utils.ProductType;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    public ProductDAO() {
        throw new IllegalStateException();
    }

    public static List<Product> getProductsFromStore(String storeName) throws SQLException {
        List<Product> products = new ArrayList<>();

        // Create Connection
        try (Connection connection = DriverManager.getConnection(Database.DB_URL, Database.USER, Database.PASS)) {
            // Create statement
            try (Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
                // Execute query
                String query = String.format("SELECT * FROM Product WHERE Store = '%s'", storeName);
                ResultSet rs = statement.executeQuery(query);
                while (rs.next()) {
                    products.add(getProduct(rs));
                }

                rs.close();
            }
        }
        return products;
    }

    public static Product getProductById(Integer id) throws SQLException, DatabaseException {
        Product product;

//            Create Connection
        try (Connection connection = DriverManager.getConnection(Database.DB_URL, Database.USER, Database.PASS)) {
//            Create Statement
            try (Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
//            Get item
                String sql = String.format("SELECT * FROM Product WHERE ProductId=%d", id);
//            Execute query
                ResultSet rs = statement.executeQuery(sql);
                if (!rs.first())
                    throw new DatabaseException("product");

                rs.first();
                product = getProduct(rs);
            }
        }
        return product;
    }

    private static Product getProduct(ResultSet rs) throws SQLException {
        ProductFactory factory = new ProductFactory();
        int id = rs.getInt("ProductId");
        String name = rs.getString("Name");
        float price = rs.getFloat("Price");
        float discountedPrice = rs.getFloat("DiscountedPrice");
        int quantity = rs.getInt("Quantity");
        String status = rs.getString("Status");
        String storeName = rs.getString("Store");
        ProductType type = ProductType.valueOf(rs.getString("Type"));
        Blob imageBlob = rs.getBlob("Image");
        InputStream stream = null;
        if (imageBlob != null)
            stream = imageBlob.getBinaryStream();

        return factory.createProduct(type, id, name, price, discountedPrice, quantity, status, stream, storeName,
                rs.getString("Size"), rs.getString("Material"), rs.getString("ShoesType"),
                rs.getString("Author"), rs.getString("Artist"), rs.getString("Plot"), rs.getString("Genre"), rs.getInt("VolumeNumber"),
                rs.getString("ConsoleType"), rs.getBoolean("DigitalOnly"),
                rs.getString("ComputerType"), rs.getInt("Ram"), rs.getInt("SSD"), rs.getString("CPU"),
                rs.getString("GPU"), rs.getInt("BatterySize"), rs.getFloat("DisplaySize"),
                rs.getString("Brand"), rs.getString("EnergyClass"), rs.getString("Specs"));
    }

    public static void saveProduct(Product product) throws SQLException {
        try (Connection connection = DriverManager.getConnection(Database.DB_URL, Database.USER, Database.PASS)) {
            PreparedStatement statement;

            String common = "Name, Price, DiscountedPrice, Quantity, Type, Status, Image, Store";
            String values = "?, ?, ?, ?, ?, ?, ?, ?";
            switch (product.getType()) {
                case CLOTHES -> {
                    ClothesProduct clothes = (ClothesProduct) product;
                    statement = connection.prepareStatement(String.format("INSERT INTO Product (%s, Size, Material) VALUES (%s, ?, ?)", common, values));
                    statement.setString(9, clothes.getClothesSize());
                    statement.setString(10, clothes.getClothesMaterial());
                }
                case SHOES -> {
                    ShoesProduct shoes = (ShoesProduct) product;
                    statement = connection.prepareStatement(String.format("INSERT INTO Product(%s, Size, Material, ShoesType) VALUES (%s, ?, ?, ?)", common, values));
                    statement.setString(9, shoes.getShoesSize());
                    statement.setString(10, shoes.getShoesMaterial());
                    statement.setString(11, shoes.getShoesType());
                }
                case BOOK -> {
                    BookProduct book = (BookProduct) product;
                    statement = connection.prepareStatement(String.format("INSERT INTO Product(%s, Author, Plot, Genre) VALUES (%s, ?, ?, ?)", common, values));
                    statement.setString(9, book.getBookAuthor());
                    statement.setString(10, book.getBookPlot());
                    statement.setString(11, book.getBookGenre());
                }
                case COMICS -> {
                    ComicsProduct comics = (ComicsProduct) product;
                    statement = connection.prepareStatement(String.format("INSERT INTO Product(%s, Author, Artist, Plot, Genre, VolumeNumber) VALUES (%s, ?, ?, ?, ?, ?)", common, values));
                    statement.setString(9, comics.getComicsAuthor());
                    statement.setString(10, comics.getComicsArtist());
                    statement.setString(11, comics.getComicsPlot());
                    statement.setString(12, comics.getComicsGenre());
                    statement.setInt(13, comics.getComicsVolume());
                }
                case VIDEOGAME -> {
                    VideoGameProduct videoGame = (VideoGameProduct) product;
                    statement = connection.prepareStatement(String.format("INSERT INTO Product(%s, Plot, Genre, ConsoleType) VALUES (%s, ?, ?, ?)", common, values));
                    statement.setString(9, videoGame.getVideoGamePlot());
                    statement.setString(10, videoGame.getVideoGameGenre());
                    statement.setString(11, videoGame.getVideoGameConsoleType());
                }
                case GAMECONSOLE -> {
                    GameConsoleProduct gameConsole = (GameConsoleProduct) product;
                    statement = connection.prepareStatement(String.format("INSERT INTO Product(%s, ConsoleType, DigitalOnly) VALUES (%s, ?, ?)", common, values));
                    statement.setString(9, gameConsole.getGameConsoleType());
                    statement.setBoolean(10, gameConsole.isDigitalOnly());
                }
                case COMPUTER -> {
                    ComputerProduct computer = (ComputerProduct) product;
                    statement = connection.prepareStatement(String.format("INSERT INTO Product(%s, ComputerType, Ram, SSD, BatterySize, CPU, GPU, Brand, DisplaySize) VALUES (%s, ?, ?, ?, ?, ?, ?, ?, ?)", common, values));
                    statement.setString(9, computer.getComputerType());
                    statement.setInt(10, computer.getComputerRam());
                    statement.setInt(11, computer.getComputerSsd());
                    statement.setInt(12, computer.getComputerBatterySize());
                    statement.setString(13, computer.getComputerCpu());
                    statement.setString(14, computer.getComputerGpu());
                    statement.setString(15, computer.getComputerBrand());
                    statement.setFloat(16, computer.getComputerDisplaySize());
                }
                case HOMEAPPLIANCES -> {
                    HomeApplianceProduct homeAppliance = (HomeApplianceProduct) product;
                    statement = connection.prepareStatement(String.format("INSERT INTO Product(%s, EnergyClass, Specs) VALUES (%s, ?, ?)", common, values));
                    statement.setString(9, homeAppliance.getHomeAppliancesEnergyClass());
                    statement.setString(10, homeAppliance.getHomeAppliancesSpecs());
                }
                default -> throw new IllegalStateException("Unexpected value: " + product.getType());
            }


            statement.setString(1, product.getName());
            statement.setFloat(2, product.getPrice());
            statement.setFloat(3, product.getDiscountedPrice());
            statement.setInt(4, product.getQuantity());
            statement.setString(5, product.getType().toString());
            statement.setString(6, product.getStatus());
            statement.setBlob(7, product.getImage());
            statement.setString(8, product.getStoreName());

            statement.executeUpdate();
            statement.close();
        }
    }

    public static void updateProduct(int productId, float price, float discountedPrice, int quantity) throws SQLException {
        try (Connection connection = DriverManager.getConnection(Database.DB_URL, Database.USER, Database.PASS)) {
            // Create statement
            try (Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
                String sql = String.format("UPDATE Product SET Price = %f, DiscountedPrice = %f, Quantity = %d WHERE ProductId =  %d", price, discountedPrice, quantity, productId);
                // Execute query
                statement.executeUpdate(sql);
            }
        }
    }

    public static void setImageOfProductId(int productId, InputStream stream) throws SQLException {
        // Create Connection
        try (Connection connection = DriverManager.getConnection(Database.DB_URL, Database.USER, Database.PASS)) {
            // Create statement
            try (PreparedStatement statement = connection.prepareStatement("UPDATE Product SET Image = ? WHERE ProductId = ?")) {
                statement.setBlob(1, stream);
                statement.setInt(2, productId);
                // Execute query
                statement.executeUpdate();
                // Clean-up dell'ambiente
            }
        }
    }
}
