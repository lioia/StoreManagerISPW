package com.example.shoppingpoint.cli.view;

import com.example.shoppingpoint.bean.NewRequestBean;
import com.example.shoppingpoint.cli.utils.CLIReader;
import com.example.shoppingpoint.exception.BeanException;

import java.io.IOException;

public class NewRequestViewCLI {
    //TODO controllo che il prodotto è veramente del negozio
    public int requestProductID() throws IOException {
        System.out.println("Product ID:");
        String productId = CLIReader.readline();
        return Integer.parseInt(productId);
    }

    public NewRequestBean newRequestInput() throws IOException, BeanException {
        System.out.println("Quantity:");
        String quantity = CLIReader.readline();
        System.out.println("Max price:");
        String maxPrice = CLIReader.readline();
        return new NewRequestBean(maxPrice,quantity);
    }
}
