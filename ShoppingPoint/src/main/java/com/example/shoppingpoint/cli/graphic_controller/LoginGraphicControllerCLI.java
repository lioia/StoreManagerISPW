package com.example.shoppingpoint.cli.graphic_controller;

import com.example.shoppingpoint.bean.LoginBean;
import com.example.shoppingpoint.cli.view.LoginViewCLI;
import com.example.shoppingpoint.controller.LoginController;
import com.example.shoppingpoint.exception.BeanException;
import com.example.shoppingpoint.exception.ControllerException;

import java.io.IOException;

public class LoginGraphicControllerCLI {
    private LoginViewCLI loginView;

    public LoginGraphicControllerCLI() {
        loginView = new LoginViewCLI();
    }

    public void login() throws BeanException, ControllerException, IOException {
        LoginBean bean = loginView.getLoginInformation();
        LoginController controller = new LoginController();
        controller.login(bean);
    }
}
