package com.example.shoppingpoint.cli.view;

import com.example.shoppingpoint.cli.utils.CLIReader;
import com.example.shoppingpoint.model.Store;

import java.io.IOException;
import java.util.List;

public class SearchStoreViewCLI {
    public void createStoresView(List<Store> stores) {
        System.out.println("--------------------");
        System.out.println("Search Store");
        for (Store store : stores) {
            System.out.printf("Store Name: %s\n", store.getName());
            System.out.printf("Address: %s\n", store.getAddress());
            System.out.printf("Type: %s\n", store.getType());
            System.out.println("------");
        }
    }

    public String getStore() throws IOException {
        System.out.print("Choose a store (type in the store name): ");
        return CLIReader.readline();
    }

    public int getAction() throws IOException {
        System.out.println("What do you want to do?");
        return CLIReader.multiChoice(List.of("Search Store", "Go back"));
    }
}
