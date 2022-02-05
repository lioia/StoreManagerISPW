package com.example.shoppingpoint.cli.graphic_controller;

import com.example.shoppingpoint.bean.RegisterBean;
import com.example.shoppingpoint.cli.view.RegisterViewCLI;
import com.example.shoppingpoint.controller.RegisterController;
import com.example.shoppingpoint.exception.BeanException;
import com.example.shoppingpoint.exception.ControllerException;

import java.io.IOException;

public class RegisterGraphicControllerCLI {
    private final RegisterViewCLI registerView;

    public RegisterGraphicControllerCLI() {
        registerView = new RegisterViewCLI();
    }

    public void register() throws BeanException, ControllerException, IOException {
        RegisterBean bean = registerView.getLoginInformation();
        RegisterController controller = new RegisterController();
        controller.register(bean);
    }
}
