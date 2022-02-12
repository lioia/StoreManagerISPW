package com.shoppingpoint.cli.graphic_controller;

import com.shoppingpoint.adapter.GenericProduct;
import com.shoppingpoint.bean.StoreBean;
import com.shoppingpoint.cli.view.StoreViewCLI;
import com.shoppingpoint.controller.LoyaltyCardController;
import com.shoppingpoint.controller.PaymentController;
import com.shoppingpoint.controller.ReviewController;
import com.shoppingpoint.controller.SendEmailController;
import com.shoppingpoint.exception.ControllerException;
import com.shoppingpoint.exception.EmailException;
import com.shoppingpoint.model.LoyaltyCard;
import com.shoppingpoint.model.Store;
import com.shoppingpoint.singleton.LoggedInUser;
import com.shoppingpoint.utils.Triple;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StoreGraphicControllerCLI {
    private static final String ERR_MESSAGE = "[ERR] ";

    public void initialize(Store store) throws IOException {
        PaymentController controller = new PaymentController();
        LoyaltyCard card = null;
        try {
            card = controller.getLoyaltyCard(LoggedInUser.getInstance().getUser().getUsername(), store.getName());
        } catch (ControllerException e) {
            System.out.println("Loyalty card not present");
        } finally {
            update(store, card);
        }
    }

    private void update(Store store, LoyaltyCard card) throws IOException {
        PaymentController controller = new PaymentController();
        StoreViewCLI view = new StoreViewCLI();
        view.showStoreInfo(store);
        view.showLoyaltyCardInfo(store, card);
        ReviewController reviewController = new ReviewController();
        List<Triple<GenericProduct, Float, Float>> productsWithValues = new ArrayList<>();
        try {
            List<GenericProduct> products = controller.getProductsFromStore(new StoreBean(store.getName()));
            for (GenericProduct p : products) {
                float review = reviewController.getAverageReviewOfProduct(p.getId());
                float discount = controller.calculateDiscountPercentage(p.getPrice(), p.getDiscountedPrice());
                productsWithValues.add(new Triple<>(p, review, discount));
            }
            boolean exit = false;
            while (!exit) {
                view.showProducts(productsWithValues);
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
                            exit = handleLoyaltyCard(store);
                            if (exit)
                                break;
                            view.showSuccessActivation();
                        }
                        break;
                    case 4: // Send email to store
                        sendEmail(store);
                        break;
                    default: // Go back
                        exit = true;
                        break;
                }
            }
        } catch (ControllerException e) {
            System.out.println(ERR_MESSAGE + e.getMessage());
        }
    }

    private void sendEmail(Store store) {
        try {
            SendEmailController sendEmailController = new SendEmailController();
            String storeOwner = sendEmailController.getUsernameOfStore(store.getName());
            sendEmailController.sendEmail(storeOwner);
        } catch (EmailException e) {
            System.out.println("[EMAIL] " + e.getMessage());
        } catch (ControllerException e) {
            System.out.println(ERR_MESSAGE + e.getMessage());
        }
    }

    private boolean handleLoyaltyCard(Store store) {
        LoyaltyCardController loyaltyCardController = new LoyaltyCardController();
        try {
            loyaltyCardController.createLoyaltyCard(LoggedInUser.getInstance().getUser().getUsername(), store.getName());
            return false;
        } catch (ControllerException e) {
            System.out.println(ERR_MESSAGE + e.getMessage());
            return true;
        }
    }
}
