package com.example.shoppingpoint.cli.graphic_controller;

import com.example.shoppingpoint.adapter.GenericProduct;
import com.example.shoppingpoint.bean.StoreBean;
import com.example.shoppingpoint.cli.view.StoreViewCLI;
import com.example.shoppingpoint.controller.LoyaltyCardController;
import com.example.shoppingpoint.controller.PaymentController;
import com.example.shoppingpoint.controller.ReviewController;
import com.example.shoppingpoint.controller.SendEmailController;
import com.example.shoppingpoint.exception.ControllerException;
import com.example.shoppingpoint.exception.DatabaseException;
import com.example.shoppingpoint.exception.EmailException;
import com.example.shoppingpoint.model.LoyaltyCard;
import com.example.shoppingpoint.model.Store;
import com.example.shoppingpoint.singleton.LoggedInUser;
import javafx.util.Pair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StoreGraphicControllerCLI {
    public void initialize(Store store) throws ControllerException, IOException, EmailException, DatabaseException {
        StoreViewCLI view = new StoreViewCLI();
        PaymentController controller = new PaymentController();
        LoyaltyCard card = controller.getLoyaltyCard(LoggedInUser.getInstance().getUser().getUsername(), store.getName());
        view.showStoreInfo(store);
        view.showLoyaltyCardInfo(store, card);
        List<GenericProduct> products = controller.getProductsFromStore(new StoreBean(store.getName()));
        ReviewController reviewController = new ReviewController();
        List<Pair<GenericProduct, Float>> productsWithReviews = new ArrayList<>();
        for (GenericProduct p : products) {
            float review = reviewController.getAverageReviewOfProduct(p.getId());
            productsWithReviews.add(new Pair<>(p, review));
        }
        view.showProducts(productsWithReviews);
        boolean exit = false;
        while (!exit) {
            int action = view.getStoreAction();
            switch (action) {
                case 1:  // Buy product
                    int productId = view.getProduct();
                    GenericProduct product = products.stream().filter(el -> el.getId().equals(productId)).findFirst().orElse(null);
                    if (product == null) {
                        System.out.println("Invalid Product ID");
                        break;
                    }
                    PaymentGraphicControllerCLI paymentGraphicControllerCLI = new PaymentGraphicControllerCLI();
                    paymentGraphicControllerCLI.initialize(store, product);
                    break;
                case 2:  // Orders
                    OrdersGraphicControllerCLI ordersGraphicControllerCLI = new OrdersGraphicControllerCLI();
                    ordersGraphicControllerCLI.initialize(store);
                    break;
                case 3:  // Activate loyalty card
                    if (card == null) {
                        LoyaltyCardController loyaltyCardController = new LoyaltyCardController();
                        loyaltyCardController.createLoyaltyCard(LoggedInUser.getInstance().getUser().getUsername(), store.getName());
                        view.showSuccessActivation();
                    }
                case 4: //send email to store
                    SendEmailController sendEmailController = new SendEmailController();
                    String storeOwner = sendEmailController.getUsernameOfStore(store.getName());
                    sendEmailController.sendEmail(storeOwner);

                    break;
                default: // Go back
                    exit = true;
                    break;
            }
        }
    }
}
