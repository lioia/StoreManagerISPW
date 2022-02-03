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
        setComicsAuthor(author);
        setComicsArtist(artist);
        setComicsPlot(plot);
        setComicsGenre(genre);
        setComicsVolume(number);
        setType(ProductType.COMICS);
    }

    public String getComicsAuthor() {
        return author;
    }

    public void setComicsAuthor(String author) {
        this.author = author;
    }

    public String getComicsArtist() {
        return artist;
    }

    public void setComicsArtist(String artist) {
        this.artist = artist;
    }

    public String getComicsPlot() {
        return plot;
    }

    public void setComicsPlot(String plot) {
        this.plot = plot;
    }

    public String getComicsGenre() {
        return genre;
    }

    public void setComicsGenre(String genre) {
        this.genre = genre;
    }

    public Integer getComicsVolume() {
        return volume;
    }

    public void setComicsVolume(Integer volume) {
        this.volume = volume;
    }
}
