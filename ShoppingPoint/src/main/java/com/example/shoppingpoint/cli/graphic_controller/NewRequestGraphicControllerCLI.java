package com.example.shoppingpoint.cli.graphic_controller;

import com.example.shoppingpoint.bean.NewRequestBean;
import com.example.shoppingpoint.cli.view.NewRequestViewCLI;
import com.example.shoppingpoint.controller.NewRequestController;
import com.example.shoppingpoint.exception.BeanException;
import com.example.shoppingpoint.exception.ControllerException;

import java.io.IOException;

public class NewRequestGraphicControllerCLI {
    public void initialize()throws IOException , BeanException, ControllerException {
        NewRequestViewCLI newRequestViewCLI = new NewRequestViewCLI();
        int productId = newRequestViewCLI.requestProductID();
        NewRequestBean newRequestBean = newRequestViewCLI.newRequestInput();
        NewRequestController controller = new NewRequestController();
        controller.saveRequest(newRequestBean,productId);
    }
}
