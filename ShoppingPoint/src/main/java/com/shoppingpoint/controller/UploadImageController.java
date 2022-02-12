package com.shoppingpoint.controller;

import com.shoppingpoint.dao.ProductDAO;
import com.shoppingpoint.exception.ControllerException;
import com.shoppingpoint.exception.ImageException;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.SQLException;

public class UploadImageController {
    public File chooseImage() {
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
        return chooser.showOpenDialog(null);
    }

    public InputStream validateImage(File image) throws ImageException {
        if (image == null)
            throw new ImageException("No image selected");
        try {
            return new FileInputStream(image);
        } catch (FileNotFoundException e) {
            throw new ImageException("Image not found");
        }
    }

    public void uploadImage(InputStream stream, int productId) throws ControllerException {
        try {
            ProductDAO.setImageOfProductId(productId, stream);
        } catch (SQLException e) {
            throw new ControllerException("SQL", e);
        }
    }
}
