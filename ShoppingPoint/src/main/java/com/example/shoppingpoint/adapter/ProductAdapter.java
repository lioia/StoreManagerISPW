package com.example.shoppingpoint.adapter;


import com.example.shoppingpoint.model.product.*;

import java.io.InputStream;

public class ProductAdapter implements GenericProduct {
    private final Product product;

    public ProductAdapter(Product product) {
        this.product = product;
    }

    @Override
    public Integer getId() {
        return this.product.getId();
    }

    @Override
    public String getName() {
        return this.product.getName();
    }

    @Override
    public Float getPrice() {
        return this.product.getPrice();
    }

    @Override
    public Float getDiscountedPrice() {
        return this.product.getDiscountedPrice();
    }

    @Override
    public Integer getQuantity() {
        return this.product.getQuantity();
    }

    @Override
    public InputStream getImage() {
        return this.product.getImage();
    }

    @Override
    public String getStatus() {
        return this.product.getStatus();
    }

    @Override
    public String getStoreName() {
        return this.product.getStoreName();
    }

    @Override
    public String getDescription() {
        switch (product.getType()) {
            case CLOTHES -> {
                ClothesProduct clothes = (ClothesProduct) product;
                return String.format("Material: %s. Size: %s", clothes.getClothesMaterial(), clothes.getClothesSize());
            }
            case SHOES -> {
                ShoesProduct shoes = (ShoesProduct) product;
                return String.format("Material: %s. Size: %s. Type: %s", shoes.getShoesMaterial(), shoes.getShoesSize(), shoes.getShoesType());
            }
            case BOOK -> {
                BookProduct book = (BookProduct) product;
                return String.format("Author: %s. Genre: %s\nPlot: %s", book.getBookAuthor(), book.getBookGenre(), book.getBookPlot());
            }
            case COMICS -> {
                ComicsProduct comics = (ComicsProduct) product;
                return String.format("Author: %s. Artist: %s\nGenre: %s. Volume Number: %d. Plot: %s", comics.getComicsAuthor(), comics.getComicsArtist(), comics.getComicsGenre(), comics.getComicsVolume(), comics.getComicsPlot());
            }
            case VIDEOGAME -> {
                VideoGameProduct videoGame = (VideoGameProduct) product;
                return String.format("Genre: %s. Console: %s\nPlot: %s", videoGame.getVideoGameGenre(), videoGame.getVideoGameConsoleType(), videoGame.getVideoGamePlot());
            }
            case GAMECONSOLE -> {
                GameConsoleProduct console = (GameConsoleProduct) product;
                return String.format("Name: %s. Digital Only: %s", console.getGameConsoleType(), console.isDigitalOnly() ? "Yes" : "No");
            }
            case COMPUTER -> {
                ComputerProduct computer = (ComputerProduct) product;
                return String.format("Type: %s. Brand: %s. Display: %f\nRAM: %dGB. SSD: %dGB. Battery (mAh): %d. CPU: %s. GPU: %s", computer.getComputerType(), computer.getComputerBrand(), computer.getComputerDisplaySize(), computer.getComputerRam(), computer.getComputerSsd(), computer.getComputerBatterySize(), computer.getComputerCpu(), computer.getComputerGpu());
            }
            case HOMEAPPLIANCES -> {
                HomeApplianceProduct homeAppliance = (HomeApplianceProduct) product;
                return String.format("Energy Class: %s. Specs: %s", homeAppliance.getHomeAppliancesEnergyClass(), homeAppliance.getHomeAppliancesSpecs());
            }
            default -> throw new IllegalStateException("Unexpected value: " + product.getType());
        }
    }

    @Override
    public void setPrice(float price) {
        product.setPrice(price);
    }

    @Override
    public void setDiscountedPrice(float discountedPrice) {
        product.setDiscountedPrice(discountedPrice);
    }

    @Override
    public void setQuantity(int quantity) {
        product.setQuantity(quantity);
    }
}
