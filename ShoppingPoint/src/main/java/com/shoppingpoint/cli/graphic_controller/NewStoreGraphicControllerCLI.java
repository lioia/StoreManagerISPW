package com.shoppingpoint.cli.graphic_controller;

import com.shoppingpoint.bean.NewStoreBean;
import com.shoppingpoint.bean.RegisterBean;
import com.shoppingpoint.cli.view.NewStoreViewCLI;
import com.shoppingpoint.controller.NewStoreController;
import com.shoppingpoint.controller.RegisterController;
import com.shoppingpoint.exception.BeanException;
import com.shoppingpoint.exception.ControllerException;
import com.shoppingpoint.model.Store;
import com.shoppingpoint.model.user.StoreOwner;
import com.shoppingpoint.singleton.LoggedInUser;
import com.shoppingpoint.graphic_controller.StoreDashboardGraphicController;

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
