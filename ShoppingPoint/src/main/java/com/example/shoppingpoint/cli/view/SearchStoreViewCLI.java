package com.example.shoppingpoint.cli.view;

import com.example.shoppingpoint.model.Store;

import java.util.List;

public class SearchStoreViewCLI {
    public void createStoresView(List<Store> stores) {
        System.out.println("Search Store");
        for(Store store : stores) {
            System.out.printf("Store Name: %s\n", store.getName());
            System.out.printf("Address: %s\n", store.getAddress());
            System.out.printf("Type: %s\n", store.getType());
        }
    }
}
