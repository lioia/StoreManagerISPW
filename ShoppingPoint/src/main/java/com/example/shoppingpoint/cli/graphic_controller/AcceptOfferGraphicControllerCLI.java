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
        Boolean exit = false;
        while (!exit)exit=acceptOfferAction();

    }

    private Boolean acceptOfferAction() throws IOException, ControllerException{
        AcceptOfferViewCLI acceptOfferViewCLI = new AcceptOfferViewCLI();
        AcceptOfferController acceptOfferController = new AcceptOfferController();
        int choice = acceptOfferViewCLI.getChoice();
        if(choice==1){
            //TODO controlo se la richiesta è stata già accettata
            int requestID = acceptOfferViewCLI.getOfferRequestId();
            List<Offer> offerList = acceptOfferController.getOffersOfRequest(requestID);
            acceptOfferViewCLI.viewOffersOfProduct(offerList);
            int choice2 = acceptOfferViewCLI.getChoice2();
            if(choice2==1){
                int offerId = acceptOfferViewCLI.acceptOffer();
                acceptOfferController.acceptOffer(requestID,offerId);
            }
            else
                return true;
        }
        if(choice==2){
            int requestID = acceptOfferViewCLI.getOfferRequestId();
            Offer offer = acceptOfferController.getAcceptedOffer(requestID);
            acceptOfferViewCLI.viewAcceptedOfferOfProduct(offer);
        }
        if(choice==3)
            return true;
        return false;
    }
}
