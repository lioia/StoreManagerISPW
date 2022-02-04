package com.example.shoppingpoint.cli.view;

import com.example.shoppingpoint.bean.LoginBean;
import com.example.shoppingpoint.cli.utils.CLIReader;
import com.example.shoppingpoint.exception.BeanException;

import java.io.IOException;

public class LoginViewCLI {
    public LoginBean getLoginInformation() throws BeanException, IOException {
        System.out.println("Login");
        System.out.println("Insert username: ");
        String login = CLIReader.readline();
        System.out.println("Insert password: ");
        String password = CLIReader.readline();
        return new LoginBean(login, password);
    }
}
