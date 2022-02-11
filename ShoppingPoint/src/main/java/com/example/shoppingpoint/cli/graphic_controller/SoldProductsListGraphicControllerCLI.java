package com.example.shoppingpoint.cli.graphic_controller;

import com.example.shoppingpoint.cli.view.SoldProductsListViewCLI;
import com.example.shoppingpoint.controller.SummaryController;
import com.example.shoppingpoint.exception.ControllerException;
import com.example.shoppingpoint.model.SoldProduct;

import java.util.List;

public class SoldProductsListGraphicControllerCLI {
    public void initialize() {
        SoldProductsListViewCLI view = new SoldProductsListViewCLI();
        SummaryController controller = new SummaryController();
        try {
            List<SoldProduct> soldProducts = controller.getSoldProducts();
            view.showLists(soldProducts);
        } catch (ControllerException e) {
            System.out.println("[ERR] " + e.getMessage());
        }
    }
}
