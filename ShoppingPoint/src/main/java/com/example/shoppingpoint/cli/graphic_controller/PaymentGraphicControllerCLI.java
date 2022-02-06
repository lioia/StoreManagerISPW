package com.example.shoppingpoint.cli.graphic_controller;

import com.example.shoppingpoint.adapter.GenericProduct;
import com.example.shoppingpoint.bean.PaymentBean;
import com.example.shoppingpoint.cli.view.PaymentViewCLI;
import com.example.shoppingpoint.controller.PaymentController;
import com.example.shoppingpoint.exception.BeanException;
import com.example.shoppingpoint.exception.ControllerException;
import com.example.shoppingpoint.model.LoyaltyCard;
import com.example.shoppingpoint.model.Store;
import com.example.shoppingpoint.singleton.LoggedInUser;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class PaymentGraphicControllerCLI {
    public void initialize(Store store, GenericProduct product) throws ControllerException, IOException {
        PaymentController controller = new PaymentController();
        LoyaltyCard card = controller.getLoyaltyCard(LoggedInUser.getInstance().getUser().getUsername(), product.getStoreName());
        PaymentViewCLI view = new PaymentViewCLI();
        int quantity = 1;
        boolean loyaltyCardCheck = false;
        while (true) {
            view.showPaymentInformation(product, card, store, quantity, loyaltyCardCheck);
            int action = view.selectAction();
            if (action == 1) { // Check loyalty card
                loyaltyCardCheck = !loyaltyCardCheck;
            } else if (action == 2) { // Increase quantity by 1
                if (quantity < product.getQuantity()) quantity++;
            } else if (action == 3) { // Decrease quantity by 1
                if (quantity > 1) quantity--;
            } else if (action == 4) { // Buy
                try {
                    controller.buy(new PaymentBean(quantity, loyaltyCardCheck), card, LoggedInUser.getInstance().getUser().getUsername(), store, product);
                } catch (BeanException e) {
                    System.out.println("Incorrect input: " + e.getMessage());
                    continue;
                }
                view.showPaymentCompleted();
                try {
                    TimeUnit.SECONDS.sleep(2); // wait for 2 seconds
                } catch (InterruptedException e) {
                    System.out.printf("Unable to wait (reason: %s)\nYou will be redirected now\n", e.getMessage());
                    break;
                }
                break;
            } else if (action == 5) break;
        }
    }
}
