package com.example.shoppingpoint.cli.view;

import com.example.shoppingpoint.cli.utils.CLIReader;

import java.io.IOException;
import java.util.List;

public class ProductViewCLI {
    public int getChoice() throws IOException {
        System.out.println("What do you want to do?");
        return CLIReader.multiChoice(List.of(
                "Edit",
                "Make request",
                "View offers",
                "View estimated price",
                "Go back"
        ));
    }
}
