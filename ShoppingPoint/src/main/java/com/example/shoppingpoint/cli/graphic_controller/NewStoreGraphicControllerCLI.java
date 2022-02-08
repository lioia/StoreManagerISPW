package com.example.shoppingpoint.cli.graphic_controller;

import com.example.shoppingpoint.bean.NewStoreBean;
import com.example.shoppingpoint.bean.RegisterBean;
import com.example.shoppingpoint.cli.view.NewStoreViewCLI;
import com.example.shoppingpoint.controller.NewStoreController;
import com.example.shoppingpoint.controller.RegisterController;
import com.example.shoppingpoint.exception.BeanException;
import com.example.shoppingpoint.exception.ControllerException;
import com.example.shoppingpoint.exception.DatabaseException;
import com.example.shoppingpoint.model.Store;
import com.example.shoppingpoint.model.user.StoreOwner;
import com.example.shoppingpoint.singleton.LoggedInUser;
import com.example.shoppingpoint.view.StoreDashboardGraphicController;

import java.io.IOException;

public class NewStoreGraphicControllerCLI {
    public void initialize(RegisterBean registerBean) throws DatabaseException,BeanException, IOException, ControllerException {
        NewStoreViewCLI view = new NewStoreViewCLI();
        NewStoreBean bean = view.getStoreInformation();
        RegisterController registerController = new RegisterController();
        NewStoreController newStoreController = new NewStoreController();
        StoreOwner user = (StoreOwner) registerController.register(registerBean);
        Store store = newStoreController.register(bean, user.getUsername());
        user.setStore(store);
        LoggedInUser.getInstance().setUser(user);
        StoreDashboardGraphicController dashboardGraphicController = new StoreDashboardGraphicController();
        dashboardGraphicController.initialize();
    }
}
