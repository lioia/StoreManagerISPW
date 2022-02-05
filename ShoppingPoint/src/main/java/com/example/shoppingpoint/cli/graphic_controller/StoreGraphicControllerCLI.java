package com.example.shoppingpoint.cli.graphic_controller;

import com.example.shoppingpoint.adapter.GenericProduct;
import com.example.shoppingpoint.bean.StoreBean;
import com.example.shoppingpoint.cli.view.StoreViewCLI;
import com.example.shoppingpoint.controller.LoyaltyCardController;
import com.example.shoppingpoint.controller.PaymentController;
import com.example.shoppingpoint.exception.ControllerException;
import com.example.shoppingpoint.model.LoyaltyCard;
import com.example.shoppingpoint.model.Store;
import com.example.shoppingpoint.singleton.LoggedInUser;

import java.io.IOException;
import java.util.List;

public class StoreGraphicControllerCLI {
    public void initialize(Store store) throws ControllerException, IOException {
        StoreViewCLI view = new StoreViewCLI();
        PaymentController controller = new PaymentController();
        LoyaltyCard card = controller.getLoyaltyCard(LoggedInUser.getInstance().getUser().getUsername(), store.getName());
        view.showStoreInfo(store);
        view.showLoyaltyCardInfo(store, card);
        List<GenericProduct> products = controller.getProductsFromStore(new StoreBean(store.getName()));
        view.showProducts(products);
        // Label to exit the while loop inside the switch
        loop:
        while (true) {
            switch (view.getStoreAction()) {
//                Buy product
                case 1 -> {
//                    TODO controllo sull'id
                    int productId = view.getProduct();
                    GenericProduct product = products.stream().filter(el -> el.getId().equals(productId)).findFirst().orElse(null);
                    if (product == null) {
                        System.out.println("Invalid Product ID");
                        continue;
                    }
                    PaymentGraphicControllerCLI paymentGraphicControllerCLI = new PaymentGraphicControllerCLI();
                    paymentGraphicControllerCLI.initialize(product);
                }
//                Orders
                case 2 -> {
//                    TODO goto orders
                }
//                Activate loyalty card
                case 3 -> {
                    if (card == null) {
                        LoyaltyCardController loyaltyCardController = new LoyaltyCardController();
                        loyaltyCardController.createLoyaltyCard(LoggedInUser.getInstance().getUser().getUsername(), store.getName());
                        view.showSuccessActivation();
                    }
                }
                case 4 -> {
                    break loop;
                }
                default -> throw new IllegalStateException("Unexpected value: " + view.getStoreAction());
            }
        }
    }
}
