package com.example.shoppingpoint.cli.view;

import com.example.shoppingpoint.cli.utils.CLIReader;
import com.example.shoppingpoint.model.Offer;

import java.io.IOException;
import java.util.List;

public class AcceptedOffersViewCLI {
    public void viewAcceptedOffer(List<Offer> acceptedOffers) {
        System.out.println("Accepted offer list");

        for (Offer offer : acceptedOffers) {
            if (offer.isChecked())
                System.out.println("NEW");
            System.out.printf("Offer ID: %d%n",offer.getOfferId());
            System.out.printf("Product name: %s%n", offer.getRequest().getProduct().getName());
            System.out.printf("Quantity: %d%n", offer.getRequest().getQuantity());
            System.out.printf("Offer price: %.2f%n", offer.getOfferPrice());
            System.out.printf("Description: %s%n", offer.getRequest().getProduct().getDescription());
            System.out.printf("Store: %s%n", offer.getRequest().getProduct().getStoreName());
            System.out.println("---------------");
        }
    }

    public boolean getEmailChoice()throws IOException {
        return CLIReader.yesOrNo("Do you want send email to store?");
    }

    public int getAcceptedOfferId()throws IOException{
        System.out.println("For which offer?");
        System.out.println("OfferId:");
        return Integer.parseInt(CLIReader.readline());
    }
}
