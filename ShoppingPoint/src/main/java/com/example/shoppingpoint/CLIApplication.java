package com.example.shoppingpoint;

import com.example.shoppingpoint.cli.graphic_controller.LoginGraphicControllerCLI;
import com.example.shoppingpoint.cli.graphic_controller.RegisterGraphicControllerCLI;
import com.example.shoppingpoint.cli.utils.CLIReader;
import com.example.shoppingpoint.exception.*;

import java.io.IOException;
import java.util.List;

public class CLIApplication {
    public static void main(String[] args) throws EmailException, BoundaryException, BeanException, ControllerException, IOException, DatabaseException {
        System.out.println("Shopping Point CLI");

        int selected = CLIReader.multiChoice(List.of("Login", "Register"));
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
