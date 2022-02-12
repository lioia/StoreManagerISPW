package com.shoppingpoint.factory;

import com.shoppingpoint.model.product.*;
import com.shoppingpoint.utils.ProductType;

import java.io.InputStream;

public class ProductFactory {
    public Product createProduct(ProductType type, int id, String name, float price, float discountedPrice, int quantity, String status, InputStream image,
                                 String storeName, String size, String material, String shoesType, String author, String artist, String plot, String genre,
                                 int volumeNumber, String consoleType, boolean digitalOnly, String computerType, int ram, int ssd, String cpu, String gpu,
                                 int batterySize, float displaySize, String brand, String energyClass, String specs) {
        Product product;
        switch (type) {
            case CLOTHES -> product = new ClothesProduct(id, name, price, discountedPrice, quantity, status, storeName, size, material);
            case SHOES -> product = new ShoesProduct(id, name, price, discountedPrice, quantity, status, storeName, size, material, shoesType);
            case BOOK -> product = new BookProduct(id, name, price, discountedPrice, quantity, status, storeName, author, plot, genre);
            case COMICS -> product = new ComicsProduct(id, name, price, discountedPrice, quantity, status, storeName, author, artist, plot, genre, volumeNumber);
            case VIDEOGAME -> product = new VideoGameProduct(id, name, price, discountedPrice, quantity, status, storeName, plot, genre, consoleType);
            case GAMECONSOLE -> product = new GameConsoleProduct(id, name, price, discountedPrice, quantity, status, storeName, consoleType, digitalOnly);
            case COMPUTER -> product = new ComputerProduct(id, name, price, discountedPrice, quantity, status, storeName, computerType, ram, ssd, batterySize, cpu, gpu, brand, displaySize);
            case HOMEAPPLIANCES -> product = new HomeApplianceProduct(id, name, price, discountedPrice, quantity, storeName, status, energyClass, specs);
            default -> throw new IllegalStateException("Unexpected value: " + type);
        }
        product.setImage(image);
        return product;
    }
}
