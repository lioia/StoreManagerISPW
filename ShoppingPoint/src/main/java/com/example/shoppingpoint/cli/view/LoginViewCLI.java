package com.example.shoppingpoint.cli.view;

import com.example.shoppingpoint.bean.LoginBean;
import com.example.shoppingpoint.cli.utils.CLIReader;
import com.example.shoppingpoint.exception.BeanException;

import java.io.IOException;

public class LoginViewCLI {
    public LoginBean getLoginInformation() throws IOException, BeanException {
        System.out.println("Login");
        System.out.print("Insert username: ");
        String login = CLIReader.readline();
        System.out.print("Insert password: ");
        String password = CLIReader.readline();
        return new LoginBean(login, password);
    }
}
