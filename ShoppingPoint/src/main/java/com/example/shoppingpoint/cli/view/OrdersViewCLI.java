package com.example.shoppingpoint.cli.view;

import com.example.shoppingpoint.cli.utils.CLIReader;
import com.example.shoppingpoint.model.Review;
import com.example.shoppingpoint.model.SoldProduct;
import javafx.util.Pair;

import java.io.IOException;
import java.util.List;

public class OrdersViewCLI {
    public void showOrdersList(List<Pair<SoldProduct, Review>> ordersWithReview) {
        System.out.println("Orders List");
        for (Pair<SoldProduct, Review> p : ordersWithReview) {
            SoldProduct soldProduct = p.getKey();
            Review review = p.getValue();
            System.out.printf("ID: %d - %s\n", review.getReviewId(), soldProduct.getProduct().getName());
            System.out.printf("Total Price: %.02f€\n", soldProduct.getQuantity() * soldProduct.getProduct().getDiscountedPrice());
            System.out.printf("Review: %.1f/5\n", review.getValue());
            System.out.println("------------------");
        }
    }

    public int selectAction() throws IOException {
        System.out.println("What do you want to do?");
        return CLIReader.multiChoice(List.of(
                "Make review for a product",
                "Go Back"
        ));
    }

    public String getProduct() throws IOException {
        System.out.print("Insert the ID: ");
        return CLIReader.readline();
    }

    public String getReview() throws IOException {
        System.out.print("Insert your review (out of 5): ");
        return CLIReader.readline();
    }
}
