package com.example.shoppingpoint.cli.graphic_controller;

import com.example.shoppingpoint.bean.SearchStoreBean;
import com.example.shoppingpoint.cli.view.SearchStoreViewCLI;
import com.example.shoppingpoint.controller.PaymentController;
import com.example.shoppingpoint.exception.ControllerException;
import com.example.shoppingpoint.model.Store;

import java.util.List;

public class SearchStoreGraphicControllerCLI {
    public void initialize() throws ControllerException {
        SearchStoreViewCLI view = new SearchStoreViewCLI();
        PaymentController controller = new PaymentController();
        List<Store> stores = controller.getStores(new SearchStoreBean());
        view.createStoresView(stores);
        int choice;
        boolean exit = false;
        while(!exit) {

        }
    }
}
