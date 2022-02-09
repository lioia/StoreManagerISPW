package com.example.shoppingpoint.cli.view;

import com.example.shoppingpoint.bean.RegisterBean;
import com.example.shoppingpoint.cli.utils.CLIReader;
import com.example.shoppingpoint.exception.BeanException;

import java.io.IOException;

public class RegisterViewCLI {
    public RegisterBean getLoginInformation() throws BeanException, IOException {
        System.out.println("--------------------");
        System.out.println("Register");
        System.out.println("Insert username: ");
        String username = CLIReader.readline();
        System.out.println("Insert valid email: ");
        String email = CLIReader.readline();
        System.out.println("Insert password: ");
        String password = CLIReader.readline();
        System.out.println("Verify password: ");
        String verifyPassword = CLIReader.readline();
        System.out.println("Choose type (Client,Store Owner,Supplier");
        String type = CLIReader.readline();

        return new RegisterBean(email, username, type, password, verifyPassword);
    }
}
