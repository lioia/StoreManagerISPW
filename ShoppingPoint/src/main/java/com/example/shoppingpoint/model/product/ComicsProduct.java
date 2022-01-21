package com.example.shoppingpoint.model.product;

import com.example.shoppingpoint.utils.ProductType;

public class ComicsProduct extends Product {
    private String author;
    private String artist;
    private String plot;
    private String genre;
    private Integer volume;

    public ComicsProduct(Integer id, String name, Float price, Float discountedPrice, Integer quantity, String status, String storeName, String author, String artist, String plot, String genre, Integer number) {
        setId(id);
        setName(name);
        setPrice(price);
        setDiscountedPrice(discountedPrice);
        setQuantity(quantity);
        setStatus(status);
        setStoreName(storeName);
        setAuthor(author);
        setArtist(artist);
        setPlot(plot);
        setGenre(genre);
        setVolume(number);
        setType(ProductType.COMICS);
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
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

    public Integer getVolume() {
        return volume;
    }

    public void setVolume(Integer volume) {
        this.volume = volume;
    }
}
