package com.example.shoppingpoint;

import com.example.shoppingpoint.cli.graphic_controller.LoginGraphicControllerCLI;
import com.example.shoppingpoint.cli.graphic_controller.RegisterGraphicControllerCLI;
import com.example.shoppingpoint.cli.utils.CLIReader;
import com.example.shoppingpoint.exception.BeanException;
import com.example.shoppingpoint.exception.ControllerException;

import java.io.IOException;

public class CLIApplication {
    public static void main(String[] args) throws BeanException, ControllerException, IOException {
        System.out.println("Shopping Point CLI");
        System.out.println("1) Login");
        System.out.println("2) Register");
        boolean exit = false;
        while(!exit) {
            String option = CLIReader.readline();
            int selected = Integer.parseInt(option);
            if(selected == 1) {
                LoginGraphicControllerCLI loginCli = new LoginGraphicControllerCLI();
                loginCli.login();
                exit = true;
            } else if (selected == 2) {
                RegisterGraphicControllerCLI registerCli = new RegisterGraphicControllerCLI();
                registerCli.register();
                exit = true;
            } else {
                System.out.println("Invalid input.");
            }
        }
    }
}
