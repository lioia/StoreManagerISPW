package com.example.shoppingpoint.cli.view;

import com.example.shoppingpoint.adapter.GenericProduct;
import com.example.shoppingpoint.cli.utils.CLIReader;
import com.example.shoppingpoint.model.LoyaltyCard;
import com.example.shoppingpoint.model.Store;

import java.io.IOException;
import java.util.List;

public class PaymentViewCLI {
    public void showPaymentInformation(GenericProduct product, LoyaltyCard card, Store store, int quantity, boolean loyaltyCardCheck) {
        System.out.println("--------------");
        System.out.println("Payment");
        System.out.println("Product Name\t\tQuantity x Price");
        float total = quantity * product.getDiscountedPrice();
        if (card != null && loyaltyCardCheck)
            total = quantity * product.getDiscountedPrice() - (float) card.getPoints() / store.getPointsInEuro();
        if (total < 0) total = 0;
        System.out.printf("%s\t\t%d (max: %d) x %.02f€\n", product.getName(), quantity, product.getQuantity(), product.getDiscountedPrice());
        System.out.printf("Total Price: %.02f€\n", total);
        if (card == null)
            System.out.println("Loyalty card unavailable");
        else
            System.out.printf("You have %d points in your loyalty card (being used: %b)\n", card.getPoints(), loyaltyCardCheck);
    }

    public int selectAction() throws IOException {
        System.out.println("What do you want to do?");
        return CLIReader.multiChoice(List.of(
                "Check loyalty card",
                "Increase quantity by 1",
                "Decrease quantity by 1",
                "Buy with PayPal",
                "Go back"
        ));
    }

    public void showPaymentCompleted() {
        System.out.println("Successfully completed payment");
        System.out.println("You will be redirected to the store page shortly");
    }
}
