package com.example.shoppingpoint.cli.graphic_controller;

import com.example.shoppingpoint.ShoppingPointApplication;
import com.example.shoppingpoint.bean.LoginBean;
import com.example.shoppingpoint.cli.view.LoginViewCLI;
import com.example.shoppingpoint.controller.LoginController;
import com.example.shoppingpoint.exception.BeanException;
import com.example.shoppingpoint.exception.ControllerException;
import com.example.shoppingpoint.model.user.Supplier;
import com.example.shoppingpoint.model.user.User;
import com.example.shoppingpoint.singleton.LoggedInUser;


import java.io.IOException;

public class LoginGraphicControllerCLI {
    private LoginViewCLI loginView;

    public LoginGraphicControllerCLI() {
        loginView = new LoginViewCLI();
    }

    public void login() throws BeanException, ControllerException, IOException {
        LoginBean bean = loginView.getLoginInformation();
        LoginController controller = new LoginController();
        User user = controller.login(bean);
        LoggedInUser.getInstance().setUser(user);
        if (user instanceof Supplier) {
            RequestListGraphicControllerCLI requestListCli = new RequestListGraphicControllerCLI();
            requestListCli.initialize();
        }


    }
}
