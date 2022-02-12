package com.shoppingpoint.cli.graphic_controller;

import com.shoppingpoint.cli.view.AcceptedOffersViewCLI;
import com.shoppingpoint.controller.AcceptedOfferController;
import com.shoppingpoint.controller.SendEmailController;
import com.shoppingpoint.exception.ControllerException;
import com.shoppingpoint.exception.EmailException;
import com.shoppingpoint.model.Offer;

import java.io.IOException;
import java.util.List;

public class AcceptedOfferGraphicControllerCLI {
    public void initialize() throws IOException {
        AcceptedOfferController acceptedOfferController = new AcceptedOfferController();
        try {
            List<Offer> acceptedOffersList = acceptedOfferController.getAcceptedOffersOfSupplier();
            AcceptedOffersViewCLI acceptedOffersViewCLI = new AcceptedOffersViewCLI();
            acceptedOffersViewCLI.viewAcceptedOffer(acceptedOffersList);
            if (acceptedOffersViewCLI.getEmailChoice()) {
                int offerId = acceptedOffersViewCLI.getAcceptedOfferId();
                Offer offer = acceptedOffersList.stream().filter(el -> el.getOfferId() == offerId).findFirst().orElse(null);
                if (offer == null) {
                    System.out.println("Error input");
                } else {
                    SendEmailController sendEmailController = new SendEmailController();
                    String storeOwner = sendEmailController.getUsernameOfStore(offer.getRequest().getProduct().getStoreName());
                    sendEmailController.sendEmail(storeOwner);
                }
            }
        } catch (ControllerException e) {
            System.out.println("[ERR] " + e.getMessage());
        } catch (EmailException e) {
            System.out.println("[EMAIL] " + e.getMessage());
        }
    }
}
