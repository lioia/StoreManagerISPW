package com.example.shoppingpoint.cli.graphic_controller;

import com.example.shoppingpoint.cli.view.AcceptedOffersViewCLI;
import com.example.shoppingpoint.controller.AcceptedOfferController;
import com.example.shoppingpoint.controller.SendEmailController;
import com.example.shoppingpoint.exception.ControllerException;
import com.example.shoppingpoint.exception.EmailException;
import com.example.shoppingpoint.model.Offer;

import java.io.IOException;
import java.util.List;

public class AcceptedOfferGraphicControllerCLI {
    public void initialize() throws EmailException,ControllerException, IOException {
        AcceptedOfferController acceptedOfferController = new AcceptedOfferController();
        List<Offer> acceptedOffersList = acceptedOfferController.getAcceptedOffersOfSupplier();
        AcceptedOffersViewCLI acceptedOffersViewCLI = new AcceptedOffersViewCLI();
        acceptedOffersViewCLI.viewAcceptedOffer(acceptedOffersList);
        if(acceptedOffersViewCLI.getEmailChoice()){
            int offerId = acceptedOffersViewCLI.getAcceptedOfferId();
            Offer offer = acceptedOffersList.stream().filter(el -> el.getOfferId()==offerId).findFirst().orElse(null);
            if(offer == null){
                System.out.println("Error input");
            }
            else {
                SendEmailController sendEmailController = new SendEmailController();
                String storeOwner = sendEmailController.getUsernameOfStore(offer.getRequest().getProduct().getStoreName());
                sendEmailController.sendEmail(storeOwner);
            }

        }
    }
}
