package com.example.shoppingpoint.cli.graphic_controller;

import com.example.shoppingpoint.adapter.GenericProduct;
import com.example.shoppingpoint.cli.view.StoreDashboardViewCLI;
import com.example.shoppingpoint.controller.ReviewController;
import com.example.shoppingpoint.controller.ViewProductsController;
import com.example.shoppingpoint.exception.BeanException;
import com.example.shoppingpoint.exception.BoundaryException;
import com.example.shoppingpoint.exception.ControllerException;
import com.example.shoppingpoint.model.Review;
import com.example.shoppingpoint.model.Store;
import com.example.shoppingpoint.model.user.StoreOwner;
import com.example.shoppingpoint.singleton.LoggedInUser;
import javafx.util.Pair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StoreDashboardGraphicControllerCLI {
    public void initialize() throws ControllerException, IOException, BeanException, BoundaryException {
        StoreDashboardViewCLI storeDashboardViewCLI = new StoreDashboardViewCLI();
        ViewProductsController viewProductsController = new ViewProductsController();
        Store store = ((StoreOwner) LoggedInUser.getInstance().getUser()).getStore();
        List<GenericProduct> productList = viewProductsController.getProductsFromStore(store);
        List<Pair<GenericProduct, Float>> productsWithReview = new ArrayList<>();
        for(GenericProduct product : productList) {
            ReviewController controller = new ReviewController();
            float review = controller.getReviewOfProduct(product.getId());
            productsWithReview.add(new Pair<>(product, review));
        }
        storeDashboardViewCLI.createProductView(productsWithReview);
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
                case 2 -> { // Select a product
                    int productId = storeDashboardViewCLI.getProduct();
                    GenericProduct product = productList.stream().filter(el -> el.getId().equals(productId)).findFirst().orElse(null);
                    if (product == null) {
                        System.out.println("Invalid product ID");
                        continue;
                    }
                    ProductGraphicControllerCLI productGraphicControllerCLI = new ProductGraphicControllerCLI();
                    productGraphicControllerCLI.initialize(product);
                }
                case 3 -> {
                    System.out.println("Loyalty card");
                    LoyaltyCardGraphicControllerCLI loyaltyCardGraphicControllerCLI = new LoyaltyCardGraphicControllerCLI();
                    loyaltyCardGraphicControllerCLI.initialize();
                }
                case 4 -> {
                    System.out.println("Client list");
                    ClientListGraphicControllerCLI clientListGraphicControllerCLI = new ClientListGraphicControllerCLI();
                    clientListGraphicControllerCLI.initialize();
                }
                case 5 -> {
                    System.out.println("Sold product");
                    //TODO
                }
                case 6 -> {
                    System.out.println("quit");
                    exit = true;
                }
            }
        }
    }
}
