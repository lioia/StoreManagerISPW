package com.example.shoppingpoint.cli.view;

import com.example.shoppingpoint.model.Offer;

import java.util.List;

public class AcceptedOffersViewCLI {
    public void viewAcceptedOffer(List<Offer> acceptedOffers) {
        System.out.println("Accepted offer list");

        for (Offer offer : acceptedOffers) {
            if (offer.isChecked())
                System.out.println("NEW");
            System.out.printf("Product name: %s\n", offer.getRequest().getProduct().getName());
            System.out.printf("Quantity: %d\n", offer.getRequest().getQuantity());
            System.out.printf("Offer price: %.2f\n", offer.getOfferPrice());
            System.out.printf("Description: %s\n", offer.getRequest().getProduct().getDescription());
            System.out.printf("Store: %s\n", offer.getRequest().getProduct().getStoreName());
            System.out.println("---------------");
        }
    }
}
