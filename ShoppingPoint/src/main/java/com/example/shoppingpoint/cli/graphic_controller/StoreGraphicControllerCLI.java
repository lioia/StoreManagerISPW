package com.example.shoppingpoint.cli.graphic_controller;

import com.example.shoppingpoint.adapter.GenericProduct;
import com.example.shoppingpoint.bean.StoreBean;
import com.example.shoppingpoint.cli.view.StoreViewCLI;
import com.example.shoppingpoint.controller.LoyaltyCardController;
import com.example.shoppingpoint.controller.PaymentController;
import com.example.shoppingpoint.controller.ReviewController;
import com.example.shoppingpoint.controller.SendEmailController;
import com.example.shoppingpoint.exception.ControllerException;
import com.example.shoppingpoint.exception.EmailException;
import com.example.shoppingpoint.model.LoyaltyCard;
import com.example.shoppingpoint.model.Store;
import com.example.shoppingpoint.singleton.LoggedInUser;
import com.example.shoppingpoint.utils.Triple;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StoreGraphicControllerCLI {
    public void initialize(Store store) throws IOException {
        StoreViewCLI view = new StoreViewCLI();
        PaymentController controller = new PaymentController();
        LoyaltyCard card = null;
        try {
            card = controller.getLoyaltyCard(LoggedInUser.getInstance().getUser().getUsername(), store.getName());
        } catch (ControllerException e) {
            System.out.println("Loyalty card not present");
        } finally {
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
                                LoyaltyCardController loyaltyCardController = new LoyaltyCardController();
                                try {
                                    loyaltyCardController.createLoyaltyCard(LoggedInUser.getInstance().getUser().getUsername(), store.getName());
                                } catch (ControllerException e) {
                                    System.out.println("[ERR] " + e.getMessage());
                                    exit = true;
                                    break;
                                }
                                view.showSuccessActivation();
                            }
                            break;
                        case 4: // Send email to store
                            try {
                                SendEmailController sendEmailController = new SendEmailController();
                                String storeOwner = sendEmailController.getUsernameOfStore(store.getName());
                                sendEmailController.sendEmail(storeOwner);
                            } catch (EmailException e) {
                                System.out.println("[EMAIL] " + e.getMessage());
                            } catch (ControllerException e) {
                                System.out.println("[ERR] " + e.getMessage());
                                exit = true;
                                break;
                            }
                            break;
                        default: // Go back
                            exit = true;
                            break;
                    }
                }
            } catch (ControllerException e) {
                System.out.println("[ERR] " + e.getMessage());
            }
        }
    }
}
