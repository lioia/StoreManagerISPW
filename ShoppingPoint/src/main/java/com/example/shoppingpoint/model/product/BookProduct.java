package com.example.shoppingpoint.model.product;

import com.example.shoppingpoint.utils.ProductType;

public class BookProduct extends Product {
    private String author;
    private String plot;
    private String genre;

    public BookProduct(Integer id, String name, Float price, Float discountedPrice, Integer quantity, String status, String author, String plot, String genre) {
        setId(id);
        setName(name);
        setPrice(price);
        setDiscountedPrice(discountedPrice);
        setQuantity(quantity);
        setStatus(status);
        setAuthor(author);
        setPlot(plot);
        setGenre(genre);
        setType(ProductType.BOOK);
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
