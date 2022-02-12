package com.shoppingpoint.cli.graphic_controller;

import com.shoppingpoint.bean.RegisterBean;
import com.shoppingpoint.cli.view.RegisterViewCLI;
import com.shoppingpoint.controller.RegisterController;
import com.shoppingpoint.exception.BeanException;
import com.shoppingpoint.exception.ControllerException;
import com.shoppingpoint.model.user.Client;
import com.shoppingpoint.model.user.Supplier;
import com.shoppingpoint.model.user.User;
import com.shoppingpoint.singleton.LoggedInUser;
import com.shoppingpoint.utils.UserType;

import java.io.IOException;

public class RegisterGraphicControllerCLI {
    private final RegisterViewCLI registerView;

    public RegisterGraphicControllerCLI() {
        registerView = new RegisterViewCLI();
    }

    public void register() throws IOException {
        while (true) {
            try {
                RegisterBean bean = registerView.getLoginInformation();
                if (bean.getUserType() == UserType.STOREOWNER) {
                    NewStoreGraphicControllerCLI newStore = new NewStoreGraphicControllerCLI();
                    newStore.initialize(bean);
                } else {
                    RegisterController controller = new RegisterController();
                    User user = controller.register(bean);
                    LoggedInUser.getInstance().setUser(user);
                    if (user instanceof Client) {
                        SearchStoreGraphicControllerCLI searchStoreGraphicControllerCLI = new SearchStoreGraphicControllerCLI();
                        searchStoreGraphicControllerCLI.initialize();
                    } else if (user instanceof Supplier) {
                        RequestListGraphicControllerCLI requestListGraphicControllerCLI = new RequestListGraphicControllerCLI();
                        requestListGraphicControllerCLI.initialize();
                    }
                }
                break;
            } catch (ControllerException | BeanException e) {
                System.out.println("[ERR] " + e.getMessage());
                System.out.println("Please retry.");
            }
        }
    }
}
