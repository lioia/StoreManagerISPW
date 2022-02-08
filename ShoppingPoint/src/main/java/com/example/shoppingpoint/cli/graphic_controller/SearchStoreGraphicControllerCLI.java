package com.example.shoppingpoint.cli.graphic_controller;

import com.example.shoppingpoint.bean.SearchStoreBean;
import com.example.shoppingpoint.cli.view.SearchStoreViewCLI;
import com.example.shoppingpoint.controller.PaymentController;
import com.example.shoppingpoint.exception.ControllerException;
import com.example.shoppingpoint.exception.DatabaseException;
import com.example.shoppingpoint.exception.EmailException;
import com.example.shoppingpoint.model.Store;

import java.io.IOException;
import java.util.List;

public class SearchStoreGraphicControllerCLI {
    public void initialize() throws EmailException, ControllerException, IOException, DatabaseException {
        SearchStoreViewCLI view = new SearchStoreViewCLI();
        PaymentController controller = new PaymentController();
        List<Store> stores = controller.getStores(new SearchStoreBean());
        view.createStoresView(stores);
        boolean exit = false;
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
    }
}
