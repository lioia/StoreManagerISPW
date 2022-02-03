package com.example.shoppingpoint.model.product;

import com.example.shoppingpoint.utils.ProductType;

public class BookProduct extends Product {
    private String author;
    private String plot;
    private String genre;

    public BookProduct(Integer id, String name, Float price, Float discountedPrice, Integer quantity, String status, String storeName, String author, String plot, String genre) {
        setId(id);
        setName(name);
        setPrice(price);
        setDiscountedPrice(discountedPrice);
        setQuantity(quantity);
        setStatus(status);
        setStoreName(storeName);
        setBookAuthor(author);
        setBookPlot(plot);
        setBookGenre(genre);
        setType(ProductType.BOOK);
    }

    public String getBookAuthor() {
        return author;
    }

    public void setBookAuthor(String author) {
        this.author = author;
    }

    public String getBookPlot() {
        return plot;
    }

    public void setBookPlot(String plot) {
        this.plot = plot;
    }

    public String getBookGenre() {
        return genre;
    }

    public void setBookGenre(String genre) {
        this.genre = genre;
    }
}
