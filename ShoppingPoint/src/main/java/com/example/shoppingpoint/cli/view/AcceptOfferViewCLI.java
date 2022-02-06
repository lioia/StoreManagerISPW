package com.example.shoppingpoint.cli.view;

import com.example.shoppingpoint.cli.utils.CLIReader;
import com.example.shoppingpoint.model.Offer;
import com.example.shoppingpoint.model.Request;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class AcceptOfferViewCLI {
    public int acceptOfferProductId() throws IOException {
        System.out.println("Product ID:");
        String productId = CLIReader.readline();
        return Integer.parseInt(productId);
    }

    public int getOfferRequestId() throws IOException {
        System.out.println("Request ID:");
        String productId = CLIReader.readline();
        return Integer.parseInt(productId);
    }

    public void viewRequestsOfProduct(List<Request> requests) {
        System.out.println("Request list");
        for (Request request : requests) {
            System.out.printf("RequestId: %d - Max Price: %.2f - Quantity: %d | ", request.getRequestId(), request.getMaxPrice(), request.getQuantity());
            if (request.isAccepted()) System.out.println("Accepted");
            else System.out.println("Not accepted");
        }
    }

    public int getChoice() throws IOException {
        System.out.println("What do you want to do?");
        return CLIReader.multiChoice(List.of("View offers of request","View accepted offer of request", "Go back"));
//        System.out.println("1) View offer of request");
//        System.out.println("2) Back");
//        boolean exit = false;
//        int selected = 0;
//        while (!exit) {
//            String option = CLIReader.readline();
//            selected = Integer.parseInt(option);
//            if (selected == 1 || selected == 2)
//                exit = true;
//            else System.out.println("Invalid input.");
//        }
//        return selected;
    }

    public int getChoice2() throws  IOException{
        System.out.println("What do you want to do?");
        return CLIReader.multiChoice(List.of("Accept offer","Go back"));
    }

    public void viewOffersOfProduct(List<Offer> offerList) {
        System.out.println("Offer list");
        for (Offer offer : offerList) {
            System.out.printf("Offer ID: %d - ", offer.getOfferId());
            System.out.printf("Price: %.2f - ", offer.getOfferPrice());
            System.out.printf("Supplier: %s\n", offer.getSupplierUsername());
        }
    }

    public int acceptOffer() throws IOException{
        System.out.println("Offer ID:");
        String productId = CLIReader.readline();
        return Integer.parseInt(productId);
    }

    public void viewAcceptedOfferOfProduct(Offer offer) {
        System.out.printf("Offer ID: %d - ", offer.getOfferId());
        System.out.printf("Price: %.2f - ", offer.getOfferPrice());
        System.out.printf("Supplier: %s\n", offer.getSupplierUsername());
    }
}
