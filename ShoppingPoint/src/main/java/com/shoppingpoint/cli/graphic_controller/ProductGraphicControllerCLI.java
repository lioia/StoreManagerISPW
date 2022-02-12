package com.shoppingpoint.cli.graphic_controller;

import com.shoppingpoint.adapter.GenericProduct;
import com.shoppingpoint.cli.view.ProductViewCLI;
import com.shoppingpoint.controller.EstimatedPriceController;
import com.shoppingpoint.exception.BoundaryException;

import java.io.IOException;

public class ProductGraphicControllerCLI {
    public void initialize(GenericProduct product) throws IOException {
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
                    EstimatedPriceController controller = new EstimatedPriceController();
                    float estimatedPrice = 0;
                    try {
                        estimatedPrice = controller.getEstimatedPrice(product.getName());
                        view.viewEstimatedPrice(estimatedPrice);
                    } catch (BoundaryException e) {
                        System.out.println("[ERR] " + e.getMessage());
                    }
                }
                default -> exit = true;
            }
        }
    }
}
