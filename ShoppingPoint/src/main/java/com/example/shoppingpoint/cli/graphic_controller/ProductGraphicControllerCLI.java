package com.example.shoppingpoint.cli.graphic_controller;

import com.example.shoppingpoint.adapter.GenericProduct;
import com.example.shoppingpoint.cli.view.ProductViewCLI;
import com.example.shoppingpoint.exception.BeanException;
import com.example.shoppingpoint.exception.ControllerException;

import java.io.IOException;

public class ProductGraphicControllerCLI {
    public void initialize(GenericProduct product) throws IOException, ControllerException, BeanException {
        ProductViewCLI view = new ProductViewCLI();
        boolean exit = false;
        while (!exit) {
            int choice = view.getChoice();
            switch (choice) {
                case 1 -> { // Edit
                    EditProductGraphicControllerCLI editProductGraphicControllerCLI = new EditProductGraphicControllerCLI();
                    editProductGraphicControllerCLI.initialize(product);
                }
                case 2 -> { // Make request
                    NewRequestGraphicControllerCLI newRequestGraphicControllerCLI = new NewRequestGraphicControllerCLI();
                    newRequestGraphicControllerCLI.initialize(product);
                }
                case 3 -> { // View Requests
                    AcceptOfferGraphicControllerCLI acceptOfferGraphicControllerCLI = new AcceptOfferGraphicControllerCLI();
                    acceptOfferGraphicControllerCLI.initialize(product);
                }
                case 4 -> { // View rating
                    RatingProductGraphicControllerCLI ratingProductGraphicControllerCLI = new RatingProductGraphicControllerCLI();
                    ratingProductGraphicControllerCLI.initialize();
                }
                case 5 -> { // View estimated price
                    EstimatePriceGraphicControllerCLI estimatePriceGraphicControllerCLI = new EstimatePriceGraphicControllerCLI();
                    estimatePriceGraphicControllerCLI.initialize();
                }
                case 6 -> exit = true;
            }
        }
    }
}
