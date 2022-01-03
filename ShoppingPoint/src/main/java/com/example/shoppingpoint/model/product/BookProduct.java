package com.example.shoppingpoint.model.product;

import com.example.shoppingpoint.utils.StatusType;

public class BookProduct extends Product {
    private String author, plot, genre;

    public BookProduct(Integer id, String name, Number price, Number discountedPrice, Integer quantity, StatusType status, String author, String plot, String genre) {
        setId(id);
        setName(name);
        setPrice(price);
        setDiscountedPrice(discountedPrice);
        setQuantity(quantity);
        setStatus(status);
        setAuthor(author);
        setPlot(plot);
        setGenre(genre);
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
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
}
