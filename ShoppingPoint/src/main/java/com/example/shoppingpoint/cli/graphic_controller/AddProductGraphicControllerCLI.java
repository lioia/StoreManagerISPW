package com.example.shoppingpoint.cli.graphic_controller;

import com.example.shoppingpoint.bean.add_product.AddProductBean;
import com.example.shoppingpoint.bean.add_product.AddProductCommonBean;
import com.example.shoppingpoint.cli.view.AddProductViewCLI;
import com.example.shoppingpoint.controller.AddProductController;
import com.example.shoppingpoint.controller.UploadImageController;
import com.example.shoppingpoint.exception.BeanException;
import com.example.shoppingpoint.exception.ControllerException;
import com.example.shoppingpoint.exception.ImageException;
import com.example.shoppingpoint.model.user.StoreOwner;
import com.example.shoppingpoint.singleton.LoggedInUser;
import com.example.shoppingpoint.utils.ProductType;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class AddProductGraphicControllerCLI {
    public void initialize() throws BeanException, IOException, ControllerException {
        AddProductViewCLI view = new AddProductViewCLI();
        AddProductCommonBean bean = view.getProductInformation();
        ProductType type = view.getType(((StoreOwner) LoggedInUser.getInstance().getUser()).getStore());
        String size = null;
        String material = null;
        String shoesType = null;
        String author = null;
        String artist = null;
        String plot = null;
        String genre = null;
        String volumeNumber = null;
        String consoleType = null;
        boolean digitalOnly = false;
        String computerType = null;
        String ram = null;
        String ssd = null;
        String cpu = null;
        String gpu = null;
        String batterySize = null;
        String displaySize = null;
        String brand = null;
        String energyClass = null;
        String specs = null;

        switch (type) {
            case CLOTHES -> {
                size = view.getInformation("Size");
                material = view.getInformation("Material");
            }
            case SHOES -> {
                size = view.getInformation("Size");
                material = view.getInformation("Material");
                shoesType = view.getInformation("Type");
            }
            case BOOK -> {
                author = view.getInformation("Author");
                genre = view.getInformation("Genre");
                plot = view.getInformation("Plot");
            }
            case COMICS -> {
                author = view.getInformation("Author");
                artist = view.getInformation("Artist");
                genre = view.getInformation("Genre");
                plot = view.getInformation("Plot");
                volumeNumber = view.getInformation("Volume");
            }
            case VIDEOGAME -> {
                genre = view.getInformation("Genre");
                plot = view.getInformation("Plot");
                consoleType = view.getChoiceInformation("Console Type", List.of("PS5", "PS4", "Xbox Series X", "Xbox Series S", "Xbox One", "Nintendo Switch"));
            }
            case GAMECONSOLE -> {
                consoleType = view.getChoiceInformation("Console Type", List.of("PS5", "PS4", "Xbox Series X", "Xbox Series S", "Xbox One", "Nintendo Switch"));
                digitalOnly = view.getYesOrNo("Digital");
            }
            case COMPUTER -> {
                computerType = view.getChoiceInformation("Computer Type", List.of("Laptop", "Desktop", "2in1", "Touch Screen"));
                ram = view.getInformation("RAM");
                ssd = view.getInformation("SSD");
                cpu = view.getInformation("CPU");
                gpu = view.getInformation("GPU");
                batterySize = view.getInformation("Battery");
                displaySize = view.getInformation("Display");
                brand = view.getInformation("Brand");
            }
            case HOMEAPPLIANCES -> {
                energyClass = view.getInformation("Energy Class");
                specs = view.getInformation("Specs");
            }
            default -> throw new IllegalStateException("Unexpected value: " + type);
        }

        boolean shouldUpload = view.getYesOrNo("Do you want to upload an image");
        while(shouldUpload) {
            String path = view.getImagePath();
            File file = new File(path);
            UploadImageController imageController = new UploadImageController();
            try {
                InputStream stream = imageController.validateImage(file);
                bean.setImage(stream);
                shouldUpload = false;
            } catch (ImageException e) {
                System.out.println("Invalid file. Try again");
            }
        }

        AddProductBean newBean = new AddProductBean(type, size, material, shoesType, author, artist, plot, genre, volumeNumber, consoleType, digitalOnly, computerType, ram, ssd, cpu, gpu, batterySize, displaySize, brand, energyClass, specs);
        AddProductController controller = new AddProductController();
        controller.saveProduct(type, newBean, bean);
    }
}
