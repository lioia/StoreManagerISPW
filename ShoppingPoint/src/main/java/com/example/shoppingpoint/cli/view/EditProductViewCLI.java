package com.example.shoppingpoint.cli.view;

import com.example.shoppingpoint.bean.store_dashboard.EditProductBean;
import com.example.shoppingpoint.cli.utils.CLIReader;
import com.example.shoppingpoint.exception.BeanException;

import java.io.IOException;

public class EditProductViewCLI {

    public EditProductBean editProductInput()throws BeanException,IOException{
        System.out.println("Price in €:");
        String price = CLIReader.readline();
        System.out.println("Discounted price in €:");
        String discountedPrice = CLIReader.readline();
        System.out.println("Quantity:");
        String quantity = CLIReader.readline();
        return new EditProductBean(price,discountedPrice,quantity);


    }
    //TODO controllo che il prodotto è veramente del negozio
    public int editProductID() throws IOException {
        System.out.println("Product ID:");
        String productId = CLIReader.readline();
        return Integer.parseInt(productId);
    }
}
