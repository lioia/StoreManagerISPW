package com.example.shoppingpoint.cli.view;

import com.example.shoppingpoint.cli.utils.CLIReader;

import java.io.IOException;

public class RatingProductViewCLI {
    public int getProductId()throws IOException {
        System.out.println("Product ID:");
        String productId = CLIReader.readline();
        return Integer.parseInt(productId);

    }
    public void viewRating(float rating){
        System.out.printf("Rating value: %.2f\n",rating);
    }
}
