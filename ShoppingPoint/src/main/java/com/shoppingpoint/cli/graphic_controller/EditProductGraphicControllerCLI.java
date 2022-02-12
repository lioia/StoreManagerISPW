package com.shoppingpoint.cli.graphic_controller;

import com.shoppingpoint.adapter.GenericProduct;
import com.shoppingpoint.bean.store_dashboard.EditProductBean;
import com.shoppingpoint.cli.view.EditProductViewCLI;
import com.shoppingpoint.controller.EditProductController;
import com.shoppingpoint.exception.BeanException;
import com.shoppingpoint.exception.ControllerException;

import java.io.IOException;

public class EditProductGraphicControllerCLI {
    public void initialize(GenericProduct product) throws IOException {
        try {
            EditProductViewCLI editProductViewCLI = new EditProductViewCLI();
            EditProductBean editProductBean = editProductViewCLI.editProductInput();
            EditProductController editProductController = new EditProductController();
            editProductController.editProduct(product.getId(), editProductBean);
        } catch (ControllerException e) {
            System.out.println("[ERR] " + e.getMessage());
        } catch (BeanException e) {
            System.out.println("Invalid input: " + e.getMessage());
        }
    }
}
