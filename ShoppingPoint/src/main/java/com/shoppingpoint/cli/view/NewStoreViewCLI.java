package com.shoppingpoint.cli.view;

import com.shoppingpoint.bean.NewStoreBean;
import com.shoppingpoint.cli.utils.CLIReader;
import com.shoppingpoint.exception.BeanException;

import java.io.IOException;

public class NewStoreViewCLI {
    public NewStoreBean getStoreInformation() throws IOException, BeanException {
        System.out.println("--------------------");
        System.out.println("New Store");
        System.out.println("Insert store name: ");
        String storeName = CLIReader.readline();
        System.out.println("Insert store address: ");
        String address = CLIReader.readline();
        System.out.println("Insert store type (Clothes, Book, VideoGame, Electronics): ");
        String storeType = CLIReader.readline();
        return new NewStoreBean(storeName, address, storeType);
    }
}
