package com.example.shoppingpoint.cli.graphic_controller;

import com.example.shoppingpoint.bean.store_dashboard.LoyaltyCardBean;
import com.example.shoppingpoint.cli.view.LoyaltyCardViewCLI;
import com.example.shoppingpoint.controller.EditLoyaltyCardController;
import com.example.shoppingpoint.controller.LoyaltyCardController;
import com.example.shoppingpoint.exception.BeanException;
import com.example.shoppingpoint.exception.ControllerException;
import com.example.shoppingpoint.model.Store;
import com.example.shoppingpoint.model.user.StoreOwner;
import com.example.shoppingpoint.singleton.LoggedInUser;

import java.io.IOException;

public class LoyaltyCardGraphicControllerCLI {
    public void initialize() throws IOException, BeanException, ControllerException {
        LoyaltyCardViewCLI loyaltyCardViewCLI = new LoyaltyCardViewCLI();
        Store store = ((StoreOwner) LoggedInUser.getInstance().getUser()).getStore();
        int choice;
        //TODO non possiamo fare nel edit carta una funzione apposita?
        if (store.getPointsInEuro() != 0 && store.getEuroInPoints() != 0) {
            choice = loyaltyCardViewCLI.getChoiceIfActive();
            if (choice == 1) {
                LoyaltyCardBean bean = loyaltyCardViewCLI.detailsInput();
                EditLoyaltyCardController editLoyaltyCardController = new EditLoyaltyCardController();
                editLoyaltyCardController.updateLoyaltyCard(bean, store);
            }
            if (choice == 2) {
                LoyaltyCardBean bean = new LoyaltyCardBean(true, 0, 0);
                EditLoyaltyCardController editLoyaltyCardController = new EditLoyaltyCardController();
                editLoyaltyCardController.updateLoyaltyCard(bean, store);
            }
        }
        else {
            choice = loyaltyCardViewCLI.getChoiceIfNotActive();
            if(choice==1) {
                LoyaltyCardBean bean = loyaltyCardViewCLI.detailsInput();
                EditLoyaltyCardController editLoyaltyCardController = new EditLoyaltyCardController();
                editLoyaltyCardController.updateLoyaltyCard(bean, store);
            }


        }

    }
}
