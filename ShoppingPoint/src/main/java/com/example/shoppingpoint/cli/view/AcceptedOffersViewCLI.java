package com.example.shoppingpoint.cli.view;

import com.example.shoppingpoint.adapter.GenericProduct;
import com.example.shoppingpoint.adapter.ProductAdapter;
import com.example.shoppingpoint.controller.AcceptedOfferController;
import com.example.shoppingpoint.controller.MakeOfferController;
import com.example.shoppingpoint.exception.ControllerException;
import com.example.shoppingpoint.model.Offer;
import com.example.shoppingpoint.model.Request;

import java.util.List;

public class AcceptedOffersViewCLI {
    public void viewAcceptedOffer(List<Offer> acceptedOffers) throws ControllerException {
        System.out.println("Accepted offer list");
        //TODO per prendere il prodotto come gestisco il controller?
        MakeOfferController controller = new MakeOfferController();
        AcceptedOfferController acceptedOfferController = new AcceptedOfferController();


        for (Offer offer : acceptedOffers) {

            Request request = acceptedOfferController.getRequestById(offer.getRequestId());
            GenericProduct product = new ProductAdapter(controller.getProduct(request.getProductId()));
            if (!acceptedOfferController.isOfferChecked(offer.getOfferId())) {
                System.out.println("NEW");
            }
            System.out.printf("Product name: %s\n", product.getName());
            System.out.printf("Quantity: %d\n", request.getQuantity());
            System.out.printf("Offer price: %.2f\n", offer.getOfferPrice());
            System.out.printf("Description: %s\n", product.getDescription());
            System.out.printf("Store: %s\n\n\n", product.getStoreName());
        }
    }
}
