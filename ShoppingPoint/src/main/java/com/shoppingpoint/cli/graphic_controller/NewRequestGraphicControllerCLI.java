package com.shoppingpoint.cli.graphic_controller;

import com.shoppingpoint.adapter.GenericProduct;
import com.shoppingpoint.bean.NewRequestBean;
import com.shoppingpoint.cli.view.NewRequestViewCLI;
import com.shoppingpoint.controller.NewRequestController;
import com.shoppingpoint.exception.BeanException;
import com.shoppingpoint.exception.ControllerException;

import java.io.IOException;

public class NewRequestGraphicControllerCLI {
    public void initialize(GenericProduct product) throws IOException {
        try {
            NewRequestViewCLI newRequestViewCLI = new NewRequestViewCLI();
            NewRequestBean newRequestBean = newRequestViewCLI.newRequestInput();
            NewRequestController controller = new NewRequestController();
            controller.saveRequest(newRequestBean, product.getId());
        } catch (ControllerException e) {
            System.out.println("[ERR] " + e.getMessage());
        } catch (BeanException e) {
            System.out.println("Invalid input: " + e.getMessage());
        }
    }
}
