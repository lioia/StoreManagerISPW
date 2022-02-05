package com.example.shoppingpoint.cli.graphic_controller;

import com.example.shoppingpoint.adapter.GenericProduct;
import com.example.shoppingpoint.cli.view.StoreDashboardViewCLI;
import com.example.shoppingpoint.controller.ViewProductsController;
import com.example.shoppingpoint.exception.BeanException;
import com.example.shoppingpoint.exception.ControllerException;
import com.example.shoppingpoint.model.Store;
import com.example.shoppingpoint.model.user.StoreOwner;
import com.example.shoppingpoint.singleton.LoggedInUser;

import java.io.IOException;
import java.util.List;

public class StoreDashboardGraphicControllerCLI {
    public void initialize() throws ControllerException, IOException, BeanException {
        StoreDashboardViewCLI storeDashboardViewCLI = new StoreDashboardViewCLI();
        ViewProductsController viewProductsController = new ViewProductsController();
        Store store = ((StoreOwner) LoggedInUser.getInstance().getUser()).getStore();
        List<GenericProduct> productList = viewProductsController.getProductsFromStore(store);
        storeDashboardViewCLI.createProductView(productList);
        int choice;
        boolean exit = false;
        while (!exit) {
            choice = storeDashboardViewCLI.getChoiceStoreOwner();
            switch (choice) {
                case 1 -> {
                    System.out.println("Add product");
                    //TODO
                    AddProductGraphicControllerCLI addProductGraphicControllerCLI = new AddProductGraphicControllerCLI();
                }
                case 2 -> {
                    System.out.println("Edit product");
                    EditProductGraphicControllerCLI editProductGraphicControllerCLI = new EditProductGraphicControllerCLI();
                    editProductGraphicControllerCLI.initialize();
                }
                case 3 -> {
                    System.out.println("Make a new request");
                    NewRequestGraphicControllerCLI newRequestGraphicControllerCLI = new NewRequestGraphicControllerCLI();
                    newRequestGraphicControllerCLI.initialize();
                }
                case 4 -> {
                    System.out.println("View request for a product");
                    AcceptOfferGraphicControllerCLI acceptOfferGraphicControllerCLI = new AcceptOfferGraphicControllerCLI();
                    acceptOfferGraphicControllerCLI.initialize();
                }
            }
        }
    }
}
