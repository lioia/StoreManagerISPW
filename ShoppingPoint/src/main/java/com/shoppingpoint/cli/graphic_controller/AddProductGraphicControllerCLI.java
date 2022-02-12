package com.shoppingpoint.cli.graphic_controller;

import com.shoppingpoint.bean.add_product.AddProductBean;
import com.shoppingpoint.bean.add_product.AddProductCommonBean;
import com.shoppingpoint.cli.view.AddProductViewCLI;
import com.shoppingpoint.controller.AddProductController;
import com.shoppingpoint.controller.UploadImageController;
import com.shoppingpoint.exception.BeanException;
import com.shoppingpoint.exception.ControllerException;
import com.shoppingpoint.exception.ImageException;
import com.shoppingpoint.model.user.StoreOwner;
import com.shoppingpoint.singleton.LoggedInUser;
import com.shoppingpoint.utils.ProductType;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class AddProductGraphicControllerCLI {
    public void initialize() throws IOException {
        AddProductViewCLI view = new AddProductViewCLI();
        try {
            AddProductCommonBean bean = view.getProductInformation();
            ProductType type = view.getType(((StoreOwner) LoggedInUser.getInstance().getUser()).getStore());
            AddProductBean newBean = new AddProductBean();

            switch (type) {
                case CLOTHES -> {
                    newBean.setSize(view.getInformation("Size"));
                    newBean.setMaterial(view.getInformation("Material"));
                }
                case SHOES -> {
                    newBean.setSize(view.getInformation("Size"));
                    newBean.setMaterial(view.getInformation("Material"));
                    newBean.setShoesType(view.getInformation("Type"));
                }
                case BOOK -> {
                    newBean.setAuthor(view.getInformation("Author"));
                    newBean.setGenre(view.getInformation("Genre"));
                    newBean.setPlot(view.getInformation("Plot"));
                }
                case COMICS -> {
                    newBean.setAuthor(view.getInformation("Author"));
                    newBean.setArtist(view.getInformation("Artist"));
                    newBean.setGenre(view.getInformation("Genre"));
                    newBean.setPlot(view.getInformation("Plot"));
                    newBean.setVolumeNumber(Integer.parseInt(view.getInformation("Volume")));
                }
                case VIDEOGAME -> {
                    newBean.setGenre(view.getInformation("Genre"));
                    newBean.setPlot(view.getInformation("Plot"));
                    newBean.setConsoleType(view.getChoiceInformation("Console Type", List.of("PS5", "PS4", "Xbox Series X", "Xbox Series S", "Xbox One", "Nintendo Switch")));
                }
                case GAMECONSOLE -> {
                    newBean.setConsoleType(view.getChoiceInformation("Console Type", List.of("PS5", "PS4", "Xbox Series X", "Xbox Series S", "Xbox One", "Nintendo Switch")));
                    newBean.setDigitalOnly(view.getYesOrNo("Digital"));
                }
                case COMPUTER -> {
                    newBean.setComputerType(view.getChoiceInformation("Computer Type", List.of("Laptop", "Desktop", "2in1", "Touch Screen")));
                    newBean.setRam(Integer.parseInt(view.getInformation("RAM")));
                    newBean.setSsd(Integer.parseInt(view.getInformation("SSD")));
                    newBean.setCpu(view.getInformation("CPU"));
                    newBean.setGpu(view.getInformation("GPU"));
                    newBean.setBatterySize(Integer.parseInt(view.getInformation("Battery")));
                    newBean.setDisplaySize(Float.parseFloat(view.getInformation("Display")));
                    newBean.setBrand(view.getInformation("Brand"));
                }
                case HOMEAPPLIANCES -> {
                    newBean.setEnergyClass(view.getInformation("Energy Class"));
                    newBean.setSpecs(view.getInformation("Specs"));
                }
                default -> throw new IllegalStateException("Unexpected value: " + type);
            }

            boolean shouldUpload = view.getYesOrNo("Do you want to upload an image");
            while (shouldUpload) {
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

            AddProductController controller = new AddProductController();
            controller.saveProduct(type, newBean, bean);
        } catch (ControllerException e) {
            System.out.println("[ERR] " + e.getMessage());
        } catch (BeanException e) {
            System.out.println("Invalid input: " + e.getMessage());
        }
    }
}
