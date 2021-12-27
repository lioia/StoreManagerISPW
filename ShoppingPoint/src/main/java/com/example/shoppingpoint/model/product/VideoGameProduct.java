package com.example.shoppingpoint.model.product;

import com.example.shoppingpoint.utils.ConsoleType;
import com.example.shoppingpoint.utils.StatusType;

public class VideoGameProduct extends Product {
    private String plot, genre;
    private ConsoleType consoleType;

    public VideoGameProduct(String name, Number price, Number discountedPrice, Integer quantity, StatusType status, String plot, String genre, ConsoleType consoleType) {
        setName(name);
        setPrice(price);
        setDiscountedPrice(discountedPrice);
        setQuantity(quantity);
        setStatus(status);
        setPlot(plot);
        setGenre(genre);
        setConsoleType(consoleType);
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

    public ConsoleType getConsoleType() {
        return consoleType;
    }

    public void setConsoleType(ConsoleType consoleType) {
        this.consoleType = consoleType;
    }
}
