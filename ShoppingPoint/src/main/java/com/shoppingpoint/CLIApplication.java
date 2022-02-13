package com.shoppingpoint;

import com.shoppingpoint.cli.graphic_controller.LoginGraphicControllerCLI;
import com.shoppingpoint.cli.graphic_controller.RegisterGraphicControllerCLI;
import com.shoppingpoint.cli.utils.CLIReader;

import java.io.IOException;
import java.util.List;

public class CLIApplication {
    public static void main(String[] args) throws IOException {
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
