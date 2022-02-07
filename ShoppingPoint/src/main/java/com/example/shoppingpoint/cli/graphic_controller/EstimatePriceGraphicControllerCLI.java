package com.example.shoppingpoint.cli.graphic_controller;

import com.example.shoppingpoint.cli.view.EstimatePriceViewCLI;
import com.example.shoppingpoint.controller.EstimatedPriceController;
import com.example.shoppingpoint.exception.BoundaryException;

import java.io.IOException;
//TODO si può togliere come classe
public class EstimatePriceGraphicControllerCLI {
    public void initialize() throws IOException, BoundaryException {
        EstimatePriceViewCLI estimatePriceViewCLI = new EstimatePriceViewCLI();
        String productName = estimatePriceViewCLI.getProductName();
        EstimatedPriceController controller = new EstimatedPriceController();
        float estimatedPrice = controller.getEstimatedPrice(productName);
        estimatePriceViewCLI.viewEstimatedPrice(estimatedPrice);
    }

}
