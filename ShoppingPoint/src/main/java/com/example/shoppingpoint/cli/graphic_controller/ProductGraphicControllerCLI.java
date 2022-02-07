package com.example.shoppingpoint.cli.graphic_controller;

import com.example.shoppingpoint.adapter.GenericProduct;
import com.example.shoppingpoint.cli.view.ProductViewCLI;
import com.example.shoppingpoint.exception.BeanException;
import com.example.shoppingpoint.exception.BoundaryException;
import com.example.shoppingpoint.exception.ControllerException;
import com.example.shoppingpoint.exception.EmailException;

import java.io.IOException;

public class ProductGraphicControllerCLI {
    public void initialize(GenericProduct product) throws EmailException,IOException, ControllerException, BeanException, BoundaryException {
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
                case 4 -> { // View estimated price
                    EstimatePriceGraphicControllerCLI estimatePriceGraphicControllerCLI = new EstimatePriceGraphicControllerCLI();
                    estimatePriceGraphicControllerCLI.initialize();
                }
                default -> exit = true;
            }
        }
    }
}
