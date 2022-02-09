package com.example.shoppingpoint.cli.graphic_controller;

import com.example.shoppingpoint.cli.view.SoldProductsListViewCLI;
import com.example.shoppingpoint.controller.SummaryController;
import com.example.shoppingpoint.exception.ControllerException;
import com.example.shoppingpoint.model.SoldProduct;

import java.util.List;

public class SoldProductsListGraphicControllerCLI {
    public void initialize() throws ControllerException {
        SoldProductsListViewCLI view = new SoldProductsListViewCLI();
        SummaryController controller = new SummaryController();
        List<SoldProduct> soldProducts = controller.getSoldProducts();
        view.showLists(soldProducts);
    }
}
