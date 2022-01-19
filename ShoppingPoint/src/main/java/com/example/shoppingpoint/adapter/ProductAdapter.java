package com.example.shoppingpoint.adapter;

import com.example.shoppingpoint.model.product.*;
import com.example.shoppingpoint.utils.ComputerType;
import com.example.shoppingpoint.utils.ConsoleType;
import com.example.shoppingpoint.utils.StatusType;
import javafx.scene.image.Image;

public class ProductAdapter implements GenericProduct {
    private final Product product;

    public ProductAdapter(Product product) {
        this.product = product;
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
    public StatusType getStatus() {
        return this.product.getStatus();
    }

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
                return String.format("Author: %s. Artist: %s\nGenre: %s. Volume Number: %d\nPlot: %s", comics.getAuthor(), comics.getArtist(), comics.getGenre(), comics.getVolume(), comics.getPlot());
            }
            case VIDEOGAME -> {
                VideoGameProduct videoGame = (VideoGameProduct) product;
                return String.format("Genre: %s. Console: %s\nPlot: %s", videoGame.getGenre(), getConsoleNameFromType(videoGame.getConsoleType()), videoGame.getPlot());
            }
            case GAMECONSOLE -> {
                GameConsoleProduct console = (GameConsoleProduct) product;
                return String.format("Name: %s. Digital Only: %s", console.getConsoleType(), console.isDigitalOnly() ? "Yes" : "No");
            }
            case COMPUTER -> {
                ComputerProduct computer = (ComputerProduct) product;
                return String.format("Type: %s. Brand: %s. Display: %f\nRAM: %dGB. SSD: %dGB. Battery (mAh): %d\nCPU: %s. GPU: %s", getComputerNameFromType(computer.getComputerType()), computer.getBrand(), computer.getDisplaySize(), computer.getRam(), computer.getSsd(), computer.getBatterySize(), computer.getCpu(), computer.getGpu());
            }
            case HOMEAPPLIANCES -> {
                HomeApplianceProduct homeAppliance = (HomeApplianceProduct) product;
                return String.format("Energy Class: %s. Specs: %s", homeAppliance.getEnergyClass(), homeAppliance.getSpecs());
            }
            default -> throw new IllegalStateException("Unexpected value: " + product.getType());
        }
    }

    private String getConsoleNameFromType(ConsoleType type) {
        switch (type) {
            case PS5 -> {
                return "PlayStation 5";
            }
            case PS4 -> {
                return "PlayStation 4";
            }
            case XBOXSERIESX -> {
                return "Xbox Series X";
            }
            case XBOXSERIESS -> {
                return "Xbox Series S";
            }
            case XBOXONE -> {
                return "Xbox One";
            }
            case NINTENDOSWITCH -> {
                return "Nintendo Switch";
            }
            default -> throw new IllegalStateException("Unexpected value: " + type);
        }
    }

    private String getComputerNameFromType(ComputerType type) {
        switch (type) {
            case LAPTOP -> {
                return "Laptop";
            }
            case DESKTOP -> {
                return "Desktop";
            }
            case TWOINONE -> {
                return "2 in 1";
            }
            case TOUCHSCREEN -> {
                return "Touch Screen";
            }
            default -> throw new IllegalStateException("Unexpected value: " + type);
        }
    }
}
