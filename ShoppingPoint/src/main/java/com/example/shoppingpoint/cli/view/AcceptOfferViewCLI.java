package com.example.shoppingpoint.cli.view;

import com.example.shoppingpoint.cli.utils.CLIReader;
import com.example.shoppingpoint.model.Offer;
import com.example.shoppingpoint.model.Request;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class AcceptOfferViewCLI {
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

    public boolean getChoice() throws IOException {
        return CLIReader.yesOrNo("Do you want to view offers? [y/n]");
    }

    public int getOfferChoice() throws IOException {
        return CLIReader.multiChoice(List.of("Do you want to accept an offer?","Do you want to send an email to a supplier?"));

    }

    public String getSupplier()throws IOException{
        System.out.println("Supplier username:");
        return CLIReader.readline();
    }

    public boolean getChoiceEmail()throws IOException {
        return CLIReader.yesOrNo("Do you want to send an email to a customer?? [y/n]");
    }

    public void viewOffersOfProduct(List<Offer> offerList) {
        System.out.println("Offer list");
        for (Offer offer : offerList) {
            System.out.printf("Offer ID: %d - ", offer.getOfferId());
            System.out.printf("Price: %.2f - ", offer.getOfferPrice());
            System.out.printf("Supplier: %s\n", offer.getSupplierUsername());
        }
    }

    public int acceptOffer() throws IOException {
        System.out.println("Offer ID:");
        String productId = CLIReader.readline();
        return Integer.parseInt(productId);
    }

    public void viewAcceptedOfferOfRequest(Offer offer) {
        System.out.printf("Offer ID: %d - ", offer.getOfferId());
        System.out.printf("Price: %.2f - ", offer.getOfferPrice());
        System.out.printf("Supplier: %s\n", offer.getSupplierUsername());
    }
}
