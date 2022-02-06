package com.example.shoppingpoint;

import com.example.shoppingpoint.cli.graphic_controller.LoginGraphicControllerCLI;
import com.example.shoppingpoint.cli.graphic_controller.RegisterGraphicControllerCLI;
import com.example.shoppingpoint.cli.utils.CLIReader;
import com.example.shoppingpoint.exception.BeanException;
import com.example.shoppingpoint.exception.BoundaryException;
import com.example.shoppingpoint.exception.ControllerException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class CLIApplication {
    public static void main(String[] args) throws BoundaryException,BeanException, ControllerException, IOException {
        System.out.println("Shopping Point CLI");

        int selected = CLIReader.multiChoice(Arrays.asList("Login", "Register"));
        switch (selected) {
            case 1 -> {
                LoginGraphicControllerCLI loginCli = new LoginGraphicControllerCLI();
                loginCli.login();
            }
            case 2 -> {
                RegisterGraphicControllerCLI registerCli = new RegisterGraphicControllerCLI();
                registerCli.register();
            }
            default -> throw new IllegalStateException("Unexpected value: " + selected);
        }
    }
}
