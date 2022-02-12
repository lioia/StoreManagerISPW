package com.shoppingpoint.cli.view;

import com.shoppingpoint.bean.NewRequestBean;
import com.shoppingpoint.cli.utils.CLIReader;
import com.shoppingpoint.exception.BeanException;

import java.io.IOException;

public class NewRequestViewCLI {
    public NewRequestBean newRequestInput() throws IOException, BeanException {
        System.out.println("Quantity:");
        String quantity = CLIReader.readline();
        System.out.println("Max price:");
        String maxPrice = CLIReader.readline();
        return new NewRequestBean(maxPrice, quantity);
    }
}
