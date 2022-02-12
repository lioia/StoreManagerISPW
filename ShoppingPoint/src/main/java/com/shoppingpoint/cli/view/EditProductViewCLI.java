package com.shoppingpoint.cli.view;

import com.shoppingpoint.bean.store_dashboard.EditProductBean;
import com.shoppingpoint.cli.utils.CLIReader;
import com.shoppingpoint.exception.BeanException;

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
}
