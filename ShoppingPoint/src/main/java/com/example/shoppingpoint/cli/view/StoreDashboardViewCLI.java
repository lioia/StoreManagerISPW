package com.example.shoppingpoint.cli.view;

import com.example.shoppingpoint.adapter.GenericProduct;
import com.example.shoppingpoint.cli.utils.CLIReader;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class StoreDashboardViewCLI {
    public int getChoiceStoreOwner() throws IOException {
        System.out.println("What do you want to do?");
        return CLIReader.multiChoice(List.of(
                "Add product",
                "Edit product",
                "Make a request of product",
                "View request for a product",
                "Change your loyalty card details",
                "View client list",
                "View sold products",
                "View rating of a product",
                "View estimated price of a product",
                "Quit"
        ));
//        System.out.println("1) Add product");
//        System.out.println("2) Edit product");
//        System.out.println("3) Make a request of product");
//        System.out.println("4) View request for a product");
//        System.out.println("5) change your loyalty card details");
//        System.out.println("6) View client list");
//        System.out.println("7) View sold products");
//        System.out.println("8) View rating of a product");
//        System.out.println("9) View estimated price of a product");
//        System.out.println("10) Quit");
//        boolean exit = false;
//        int selected = 0;
//        while (!exit) {
//            String option = CLIReader.readline();
//            selected = Integer.parseInt(option);
//            if (selected > 0 && selected <= 10)
//                exit = true;
//            else System.out.println("Invalid input.");
//        }
//        return selected;
    }

    public void createProductView(List<GenericProduct> productList) {
        System.out.println("\nProduct list\n");

        for (GenericProduct product : productList) {
            System.out.printf("Product ID: %d\n", product.getId());
            System.out.printf("Name: %s\n", product.getName());
            System.out.printf("Price: %.2f\n", product.getPrice());
            System.out.printf("Discounted price: %.2f\n", product.getDiscountedPrice());
            System.out.printf("Status: %s\n", product.getStatus());
            System.out.printf("Quantity: %d\n", product.getQuantity());
            System.out.printf("Description: %s\n", product.getDescription());
            System.out.println("---------------");
        }
    }
}
