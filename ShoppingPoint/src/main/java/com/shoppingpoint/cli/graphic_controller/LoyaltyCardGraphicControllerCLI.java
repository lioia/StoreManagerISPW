package com.shoppingpoint.cli.graphic_controller;

import com.shoppingpoint.bean.store_dashboard.LoyaltyCardBean;
import com.shoppingpoint.cli.view.LoyaltyCardViewCLI;
import com.shoppingpoint.controller.EditLoyaltyCardController;
import com.shoppingpoint.exception.BeanException;
import com.shoppingpoint.exception.ControllerException;
import com.shoppingpoint.model.Store;
import com.shoppingpoint.model.user.StoreOwner;
import com.shoppingpoint.singleton.LoggedInUser;

import java.io.IOException;

public class LoyaltyCardGraphicControllerCLI {
    public void initialize() throws IOException {
        LoyaltyCardViewCLI loyaltyCardViewCLI = new LoyaltyCardViewCLI();
        Store store = ((StoreOwner) LoggedInUser.getInstance().getUser()).getStore();
        try {
            int choice;
            if (store.getPointsInEuro() != 0 && store.getEuroInPoints() != 0) { // Loyalty Card Enabled
                loyaltyCardViewCLI.viewCardDetails(store.getPointsInEuro(), store.getEuroInPoints());
                choice = loyaltyCardViewCLI.getChoiceIfActive();
                if (choice == 1) updatePoints(loyaltyCardViewCLI, store);
                if (choice == 2) {
                    EditLoyaltyCardController editLoyaltyCardController = new EditLoyaltyCardController();
                    editLoyaltyCardController.disableLoyaltyCard(store);
                }
            } else { // disabled
                boolean activate = loyaltyCardViewCLI.getChoiceIfNotActive();
                if (activate) updatePoints(loyaltyCardViewCLI, store);
            }
        } catch (ControllerException e) {
            System.out.println("[ERR] " + e.getMessage());
        }
    }

    private void updatePoints(LoyaltyCardViewCLI view, Store store) throws IOException, ControllerException {
        try {
            LoyaltyCardBean bean = view.detailsInput();
            EditLoyaltyCardController editLoyaltyCardController = new EditLoyaltyCardController();
            editLoyaltyCardController.updateLoyaltyCard(bean, store);
        } catch (BeanException e) {
            System.out.println("Invalid input: " + e.getMessage());
        }
    }
}
