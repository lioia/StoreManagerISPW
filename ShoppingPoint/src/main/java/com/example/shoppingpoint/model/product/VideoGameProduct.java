package com.example.shoppingpoint.model.product;

import com.example.shoppingpoint.utils.ProductType;

public class VideoGameProduct extends Product {
    private String plot;
    private String genre;
    private String consoleType;

    public VideoGameProduct(Integer id, String name, Float price, Float discountedPrice, Integer quantity, String status, String storeName, String plot, String genre, String consoleType) {
        setId(id);
        setName(name);
        setPrice(price);
        setDiscountedPrice(discountedPrice);
        setQuantity(quantity);
        setStatus(status);
        setStoreName(storeName);
        setPlot(plot);
        setGenre(genre);
        setConsoleType(consoleType);
        setType(ProductType.VIDEOGAME);
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getConsoleType() {
        return consoleType;
    }

    public void setConsoleType(String consoleType) {
        this.consoleType = consoleType;
    }
}
