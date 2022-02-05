package com.example.shoppingpoint.cli.view;

import com.example.shoppingpoint.cli.utils.CLIReader;
import com.example.shoppingpoint.model.Offer;
import com.example.shoppingpoint.model.Request;

import java.io.IOException;
import java.util.List;

public class AcceptOfferViewCLI {
    public int acceptOfferProductId()throws IOException {
        System.out.println("Product ID:");
        String productId = CLIReader.readline();
        return Integer.parseInt(productId);
    }

    public int acceptOfferRequestId()throws IOException {
        System.out.println("Request ID:");
        String productId = CLIReader.readline();
        return Integer.parseInt(productId);
    }

    public void viewRequestsOfProduct(List<Request> requests){
        System.out.println("Request list");
        for( Request request : requests){
            System.out.printf("RequestId: %d",request.getRequestId());
            System.out.printf("Max price: %.2f",request.getMaxPrice());
            System.out.printf("Quantity:",request.getQuantity());
            if(request.isAccepted())System.out.println("Accepted");
            else System.out.println("Not accepted");
        }
    }

    public int getChoice() throws IOException{
        System.out.println("What do you want to do?");
        System.out.println("1) View offer of request");
        System.out.println("2) Back");
        Boolean exit=false;
        int selected=0;
        while (!exit) {
            String option = CLIReader.readline();
            selected = Integer.parseInt(option);
            if(selected==1 || selected==2)
                exit=true;
            else System.out.println("Invalid input.");
        }
        return selected;
    }

    public void viewOffersOfProduct(List<Offer> offerList){
        System.out.println("Offer list");
        for(Offer offer: offerList){
            System.out.printf("Offer ID: %d",offer.getOfferId());
            System.out.printf("Price: %.2f",offer.getOfferPrice());
            System.out.printf("Supplier: %s",offer.getSupplierUsername());
        }
    }


}
