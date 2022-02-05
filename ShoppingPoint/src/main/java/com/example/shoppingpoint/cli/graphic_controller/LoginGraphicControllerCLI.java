package com.example.shoppingpoint.cli.graphic_controller;

import com.example.shoppingpoint.bean.LoginBean;
import com.example.shoppingpoint.cli.view.LoginViewCLI;
import com.example.shoppingpoint.controller.LoginController;
import com.example.shoppingpoint.exception.BeanException;
import com.example.shoppingpoint.exception.ControllerException;
import com.example.shoppingpoint.model.user.Client;
import com.example.shoppingpoint.model.user.StoreOwner;
import com.example.shoppingpoint.model.user.Supplier;
import com.example.shoppingpoint.model.user.User;
import com.example.shoppingpoint.singleton.LoggedInUser;

import java.io.IOException;

public class LoginGraphicControllerCLI {
    private final LoginViewCLI loginView;

    public LoginGraphicControllerCLI() {
        loginView = new LoginViewCLI();
    }

    public void login() throws IOException, BeanException,ControllerException{
        User user;
        while (true) {
            try {
                LoginBean bean = loginView.getLoginInformation();
                LoginController controller = new LoginController();
                user = controller.login(bean);
                LoggedInUser.getInstance().setUser(user);
                break;
            } catch (ControllerException e) {
                System.out.println("[ERR] " + e.getMessage());
                System.out.println("Please retry.");
            }
        }
        if(user instanceof Client) {
            SearchStoreGraphicControllerCLI searchStoreGraphicControllerCLI = new SearchStoreGraphicControllerCLI();
            searchStoreGraphicControllerCLI.initialize();
        }
        if (user instanceof StoreOwner){
            StoreDashboardGraphicControllerCLI storeDashboardGraphicControllerCLI = new StoreDashboardGraphicControllerCLI();
            storeDashboardGraphicControllerCLI.initialize();
        }
        if (user instanceof Supplier) {
            RequestListGraphicControllerCLI requestListCli = new RequestListGraphicControllerCLI();
            requestListCli.initialize();
        }
    }
}
