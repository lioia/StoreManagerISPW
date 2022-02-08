package com.example.shoppingpoint.cli.graphic_controller;

import com.example.shoppingpoint.bean.RegisterBean;
import com.example.shoppingpoint.cli.view.RegisterViewCLI;
import com.example.shoppingpoint.controller.RegisterController;
import com.example.shoppingpoint.exception.BeanException;
import com.example.shoppingpoint.exception.ControllerException;
import com.example.shoppingpoint.exception.DatabaseException;
import com.example.shoppingpoint.exception.EmailException;
import com.example.shoppingpoint.model.user.Client;
import com.example.shoppingpoint.model.user.Supplier;
import com.example.shoppingpoint.model.user.User;
import com.example.shoppingpoint.singleton.LoggedInUser;
import com.example.shoppingpoint.utils.UserType;

import java.io.IOException;

public class RegisterGraphicControllerCLI {
    private final RegisterViewCLI registerView;

    public RegisterGraphicControllerCLI() {
        registerView = new RegisterViewCLI();
    }

    public void register() throws EmailException, BeanException, ControllerException, IOException, DatabaseException {
        RegisterBean bean = registerView.getLoginInformation();
        if(bean.getUserType() == UserType.STOREOWNER) {
            NewStoreGraphicControllerCLI newStore = new NewStoreGraphicControllerCLI();
            newStore.initialize(bean);
        } else {
            RegisterController controller = new RegisterController();
            User user = controller.register(bean);
            LoggedInUser.getInstance().setUser(user);
            if(user instanceof Client) {
                SearchStoreGraphicControllerCLI searchStoreGraphicControllerCLI = new SearchStoreGraphicControllerCLI();
                searchStoreGraphicControllerCLI.initialize();
            } else if(user instanceof Supplier) {
                RequestListGraphicControllerCLI requestListGraphicControllerCLI = new RequestListGraphicControllerCLI();
                requestListGraphicControllerCLI.initialize();
            }
        }
    }
}
