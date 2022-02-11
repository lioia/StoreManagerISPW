package com.example.shoppingpoint.cli.graphic_controller;

import com.example.shoppingpoint.bean.NewStoreBean;
import com.example.shoppingpoint.bean.RegisterBean;
import com.example.shoppingpoint.cli.view.NewStoreViewCLI;
import com.example.shoppingpoint.controller.NewStoreController;
import com.example.shoppingpoint.controller.RegisterController;
import com.example.shoppingpoint.exception.BeanException;
import com.example.shoppingpoint.exception.ControllerException;
import com.example.shoppingpoint.model.Store;
import com.example.shoppingpoint.model.user.StoreOwner;
import com.example.shoppingpoint.singleton.LoggedInUser;
import com.example.shoppingpoint.view.StoreDashboardGraphicController;

import java.io.IOException;

public class NewStoreGraphicControllerCLI {
    public void initialize(RegisterBean registerBean) throws IOException {
        NewStoreViewCLI view = new NewStoreViewCLI();
        RegisterController registerController = new RegisterController();
        NewStoreController newStoreController = new NewStoreController();
        while (true) {
            try {
                NewStoreBean bean = view.getStoreInformation();
                StoreOwner user = (StoreOwner) registerController.register(registerBean);
                Store store = newStoreController.register(bean, user.getUsername());
                user.setStore(store);
                LoggedInUser.getInstance().setUser(user);
                StoreDashboardGraphicController dashboardGraphicController = new StoreDashboardGraphicController();
                dashboardGraphicController.initialize();
                break;
            } catch (ControllerException | BeanException e) {
                System.out.println("[ERR] " + e.getMessage());
                System.out.println("Try again.");
            }
        }
    }
}
