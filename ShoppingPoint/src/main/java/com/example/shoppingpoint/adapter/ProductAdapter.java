package com.example.shoppingpoint.adapter;


import com.example.shoppingpoint.model.product.*;
import javafx.scene.image.Image;

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
    public Image getImage() {
        return this.product.getImage();
    }

    @Override
    public String getStatus() {
        return this.product.getStatus();
    }

    @Override
    public String getStore(){return this.product.getStoreName();}

    @Override
    public String getDescription() {
        switch (product.getType()) {
            case CLOTHES -> {
                ClothesProduct clothes = (ClothesProduct) product;
                return String.format("Material: %s. Size: %s", clothes.getMaterial(), clothes.getSize());
            }
            case SHOES -> {
                ShoesProduct shoes = (ShoesProduct) product;
                return String.format("Material: %s. Size: %s. Type: %s", shoes.getMaterial(), shoes.getSize(), shoes.getShoesType());
            }
            case BOOK -> {
                BookProduct book = (BookProduct) product;
                return String.format("Author: %s. Genre: %s\nPlot: %s", book.getAuthor(), book.getGenre(), book.getPlot());
            }
            case COMICS -> {
                ComicsProduct comics = (ComicsProduct) product;
                return String.format("Author: %s. Artist: %s\nGenre: %s. Volume Number: %d. Plot: %s", comics.getAuthor(), comics.getArtist(), comics.getGenre(), comics.getVolume(), comics.getPlot());
            }
            case VIDEOGAME -> {
                VideoGameProduct videoGame = (VideoGameProduct) product;
                return String.format("Genre: %s. Console: %s\nPlot: %s", videoGame.getGenre(), videoGame.getConsoleType(), videoGame.getPlot());
            }
            case GAMECONSOLE -> {
                GameConsoleProduct console = (GameConsoleProduct) product;
                return String.format("Name: %s. Digital Only: %s", console.getConsoleType(), console.isDigitalOnly() ? "Yes" : "No");
            }
            case COMPUTER -> {
                ComputerProduct computer = (ComputerProduct) product;
                return String.format("Type: %s. Brand: %s. Display: %f\nRAM: %dGB. SSD: %dGB. Battery (mAh): %d. CPU: %s. GPU: %s", computer.getComputerType(), computer.getBrand(), computer.getDisplaySize(), computer.getRam(), computer.getSsd(), computer.getBatterySize(), computer.getCpu(), computer.getGpu());
            }
            case HOMEAPPLIANCES -> {
                HomeApplianceProduct homeAppliance = (HomeApplianceProduct) product;
                return String.format("Energy Class: %s. Specs: %s", homeAppliance.getEnergyClass(), homeAppliance.getSpecs());
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
