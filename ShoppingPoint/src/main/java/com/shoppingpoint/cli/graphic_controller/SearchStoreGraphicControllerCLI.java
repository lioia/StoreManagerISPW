package com.shoppingpoint.cli.graphic_controller;

import com.shoppingpoint.bean.SearchStoreBean;
import com.shoppingpoint.cli.view.SearchStoreViewCLI;
import com.shoppingpoint.controller.PaymentController;
import com.shoppingpoint.exception.ControllerException;
import com.shoppingpoint.model.Store;

import java.io.IOException;
import java.util.List;

public class SearchStoreGraphicControllerCLI {
    public void initialize() throws IOException {
        SearchStoreViewCLI view = new SearchStoreViewCLI();
        PaymentController controller = new PaymentController();
        List<Store> stores;
        try {
            stores = controller.getStores(new SearchStoreBean());
            boolean exit = false;
            view.createStoresView(stores);
            while (!exit) {
                int action = view.getAction();
                switch (action) {
                    case 1 -> {
                        String storeName = view.getStore();
                        Store store = stores.stream().filter(el -> el.getName().equals(storeName)).findFirst().orElse(null);
                        if (store == null) {
                            System.out.println("Invalid input");
                            break;
                        }
                        StoreGraphicControllerCLI storeGraphicControllerCLI = new StoreGraphicControllerCLI();
                        storeGraphicControllerCLI.initialize(store);
                    }
                    case 2 -> exit = true;
                }
            }
        } catch (ControllerException e) {
            System.out.println("[ERR] " + e.getMessage());
        }
    }
}
