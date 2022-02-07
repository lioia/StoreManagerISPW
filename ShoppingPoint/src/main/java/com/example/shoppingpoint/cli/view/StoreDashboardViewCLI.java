package com.example.shoppingpoint.cli.view;

import com.example.shoppingpoint.adapter.GenericProduct;
import com.example.shoppingpoint.cli.utils.CLIReader;
import javafx.util.Pair;

import java.io.IOException;
import java.util.List;

public class StoreDashboardViewCLI {
    public int getChoiceStoreOwner() throws IOException {
        System.out.println("What do you want to do?");
        return CLIReader.multiChoice(List.of(
                "Add product",
                "Select a product",
                "Change your loyalty card details",
                "View client list",
                "View sold products",
                "View products list",
                "Quit"
        ));
    }

    public int getProduct() throws IOException {
        System.out.println("Select a product (insert product  ID): ");
        return Integer.parseInt(CLIReader.readline());
    }

    public void createProductView(List<Pair<GenericProduct, Float>> productList) {
        System.out.println("\nProduct list\n");

        for (Pair<GenericProduct, Float> productWithReview : productList) {
            GenericProduct product = productWithReview.getKey();
            float review = productWithReview.getValue();
            System.out.printf("Product ID: %d\n", product.getId());
            System.out.printf("Name: %s\n", product.getName());
            System.out.printf("Price: %.2f\n", product.getPrice());
            System.out.printf("Discounted price: %.2f\n", product.getDiscountedPrice());
            System.out.printf("Status: %s\n", product.getStatus());
            System.out.printf("Quantity: %d\n", product.getQuantity());
            System.out.printf("Description: %s\n", product.getDescription());
            System.out.printf("Review: %.1f\n", review);
            System.out.println("---------------");
        }
    }
}
