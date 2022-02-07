package com.example.shoppingpoint.cli.view;

import com.example.shoppingpoint.cli.utils.CLIReader;

import java.io.IOException;
//TODO si pio togliere come classe
public class EstimatePriceViewCLI {
    public String getProductName()throws IOException {
        System.out.println("Product name:");
        return CLIReader.readline();
    }

    public void viewEstimatedPrice(float estimatedPrice){
        System.out.printf("Estimated price: %.2f\n",estimatedPrice);
    }
}
