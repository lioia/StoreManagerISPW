package com.example.shoppingpoint.controller;

import com.example.shoppingpoint.dao.ProductDAO;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class UploadImageController {
    public File uploadImage(int productId) throws Exception {
        FileChooser chooser = new FileChooser();
        //Set extension filter
        FileChooser.ExtensionFilter extFilterJPG
                = new FileChooser.ExtensionFilter("JPG files (*.JPG)", "*.JPG");
        FileChooser.ExtensionFilter extFilterjpg
                = new FileChooser.ExtensionFilter("jpg files (*.jpg)", "*.jpg");
        FileChooser.ExtensionFilter extFilterPNG
                = new FileChooser.ExtensionFilter("PNG files (*.PNG)", "*.PNG");
        FileChooser.ExtensionFilter extFilterpng
                = new FileChooser.ExtensionFilter("png files (*.png)", "*.png");
        chooser.getExtensionFilters()
                .addAll(extFilterJPG, extFilterjpg, extFilterPNG, extFilterpng);
        File image = chooser.showOpenDialog(null);
        if (image != null) {
            InputStream stream = new FileInputStream(image);
            ProductDAO.setImageOfProductId(productId, stream);
        }
        return image;
    }
}
