package com.example.shoppingpoint.cli.graphic_controller;

import com.example.shoppingpoint.cli.view.AcceptOfferViewCLI;
import com.example.shoppingpoint.controller.AcceptOfferController;
import com.example.shoppingpoint.exception.ControllerException;
import com.example.shoppingpoint.model.Offer;
import com.example.shoppingpoint.model.Request;

import java.io.IOException;
import java.util.List;

public class AcceptOfferGraphicControllerCLI {
    public void initialize() throws IOException, ControllerException {
        AcceptOfferViewCLI acceptOfferViewCLI = new AcceptOfferViewCLI();
        int productId = acceptOfferViewCLI.acceptOfferProductId();
        AcceptOfferController acceptOfferController = new AcceptOfferController();
        List<Request> requestsList = acceptOfferController.getRequestsOfProduct(productId);
        acceptOfferViewCLI.viewRequestsOfProduct(requestsList);
        int choice = acceptOfferViewCLI.getChoice();
        if(choice==1){
            int requestID = acceptOfferViewCLI.acceptOfferRequestId();
            List<Offer> offerList = acceptOfferController.getOffersOfRequest(requestID);
            acceptOfferViewCLI.viewOffersOfProduct(offerList);


        }
    }
}
