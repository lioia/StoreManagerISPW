package com.example.shoppingpoint.cli.graphic_controller;

import com.example.shoppingpoint.cli.view.AcceptedOffersViewCLI;
import com.example.shoppingpoint.controller.AcceptedOfferController;
import com.example.shoppingpoint.exception.ControllerException;
import com.example.shoppingpoint.model.Offer;

import java.util.List;

public class AcceptedOfferGraphicControllerCLI {
    public void initialize() throws ControllerException {
        AcceptedOfferController acceptedOfferController = new AcceptedOfferController();
        List<Offer> acceptedOffersList = acceptedOfferController.getAcceptedOffersOfSupplier();
        AcceptedOffersViewCLI acceptedOffersViewCLI = new AcceptedOffersViewCLI();
        acceptedOffersViewCLI.viewAcceptedOffer(acceptedOffersList);
    }
}
