package com.example.shoppingpoint.cli.graphic_controller;

import com.example.shoppingpoint.adapter.GenericProduct;
import com.example.shoppingpoint.bean.StoreBean;
import com.example.shoppingpoint.cli.view.StoreViewCLI;
import com.example.shoppingpoint.controller.LoyaltyCardController;
import com.example.shoppingpoint.controller.PaymentController;
import com.example.shoppingpoint.controller.ReviewController;
import com.example.shoppingpoint.exception.ControllerException;
import com.example.shoppingpoint.model.LoyaltyCard;
import com.example.shoppingpoint.model.Store;
import com.example.shoppingpoint.singleton.LoggedInUser;
import javafx.util.Pair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StoreGraphicControllerCLI {
    public void initialize(Store store) throws ControllerException, IOException {
        StoreViewCLI view = new StoreViewCLI();
        PaymentController controller = new PaymentController();
        LoyaltyCard card = controller.getLoyaltyCard(LoggedInUser.getInstance().getUser().getUsername(), store.getName());
        view.showStoreInfo(store);
        view.showLoyaltyCardInfo(store, card);
        List<GenericProduct> products = controller.getProductsFromStore(new StoreBean(store.getName()));
        ReviewController reviewController = new ReviewController();
        List<Pair<GenericProduct, Float>> productsWithReviews = new ArrayList<>();
        for (GenericProduct p : products) {
            float review = reviewController.getReviewOfProduct(p.getId());
            productsWithReviews.add(new Pair<>(p, review));
        }
        view.showProducts(productsWithReviews);
        while (true) {
            int action = view.getStoreAction();
            if (action == 1) { // Buy product
                int productId = view.getProduct();
                GenericProduct product = products.stream().filter(el -> el.getId().equals(productId)).findFirst().orElse(null);
                if (product == null) {
                    System.out.println("Invalid Product ID");
                    continue;
                }
                PaymentGraphicControllerCLI paymentGraphicControllerCLI = new PaymentGraphicControllerCLI();
                paymentGraphicControllerCLI.initialize(store, product);
            } else if (action == 2) { // Orders
                OrdersGraphicControllerCLI ordersGraphicControllerCLI = new OrdersGraphicControllerCLI();
                ordersGraphicControllerCLI.initialize(store);
            } else if (action == 3) { // Activate loyalty card
                if (card == null) {
                    LoyaltyCardController loyaltyCardController = new LoyaltyCardController();
                    loyaltyCardController.createLoyaltyCard(LoggedInUser.getInstance().getUser().getUsername(), store.getName());
                    view.showSuccessActivation();
                }
            } else // Go back
                if (action == 4) break;
        }
    }
}
