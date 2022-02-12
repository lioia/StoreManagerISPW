package com.shoppingpoint.cli.view;

import com.shoppingpoint.cli.utils.CLIReader;

import java.io.IOException;
import java.util.List;

public class ProductViewCLI {
    public int getChoice() throws IOException {
        System.out.println("What do you want to do?");
        return CLIReader.multiChoice(List.of(
                "Edit",
                "Make request",
                "View requests",
                "View estimated price",
                "Go back"
        ));
    }

    public void viewEstimatedPrice(float estimatedPrice) {
        System.out.printf("Estimated price: %.2f%n", estimatedPrice);
    }
}
