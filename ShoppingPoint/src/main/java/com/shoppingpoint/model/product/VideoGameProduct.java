package com.shoppingpoint.model.product;

import com.shoppingpoint.utils.ProductType;

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
        setVideoGamePlot(plot);
        setVideoGameGenre(genre);
        setVideoGameConsoleType(consoleType);
        setType(ProductType.VIDEOGAME);
    }

    public String getVideoGamePlot() {
        return plot;
    }

    public void setVideoGamePlot(String plot) {
        this.plot = plot;
    }

    public String getVideoGameGenre() {
        return genre;
    }

    public void setVideoGameGenre(String genre) {
        this.genre = genre;
    }

    public String getVideoGameConsoleType() {
        return consoleType;
    }

    public void setVideoGameConsoleType(String consoleType) {
        this.consoleType = consoleType;
    }
}
