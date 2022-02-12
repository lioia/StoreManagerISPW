package com.shoppingpoint.cli.view;

import com.shoppingpoint.model.SoldProduct;

import java.util.List;

public class SoldProductsListViewCLI {
    public void showLists(List<SoldProduct> soldProducts) {
        System.out.println("--------------------");
        System.out.println("Sold Products List");
        for (SoldProduct product : soldProducts) {
            System.out.println(product.getProduct().getName());
            System.out.println("Bought By: " + product.getClient().getUsername());
            System.out.println("Quantity: " + product.getQuantity());
            System.out.println("Date: " + product.getDate().toString());
        }
    }
}
