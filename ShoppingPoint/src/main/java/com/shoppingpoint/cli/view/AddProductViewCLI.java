package com.shoppingpoint.cli.view;

import com.shoppingpoint.bean.add_product.AddProductCommonBean;
import com.shoppingpoint.cli.utils.CLIReader;
import com.shoppingpoint.exception.BeanException;
import com.shoppingpoint.model.Store;
import com.shoppingpoint.utils.ProductType;
import com.shoppingpoint.utils.StoreType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AddProductViewCLI {
    public AddProductCommonBean getProductInformation() throws IOException, BeanException {
        System.out.println("--------------------");
        System.out.println("Add Product");
        System.out.println("Insert name: ");
        String name = CLIReader.readline();
        System.out.println("Insert price: ");
        String price = CLIReader.readline();
        System.out.println("Insert discounted price: ");
        String discountedPrice = CLIReader.readline();
        System.out.println("Insert quantity: ");
        String quantity = CLIReader.readline();
        System.out.println("Insert status: ");
        String status = CLIReader.multiChoiceString(List.of("New", "Used", "Used Like New", "Regenerated"));
        return new AddProductCommonBean(name, price, discountedPrice, quantity, status);
    }

    public ProductType getType(Store store) throws IOException {
        List<String> types = new ArrayList<>();
        switch (store.getType()) {
            case CLOTHES -> types.addAll(List.of("Clothes", "Shoes"));
            case BOOKS -> types.addAll(List.of("Book", "Comics"));
            case VIDEOGAMES -> types.addAll(List.of("Video Game", "Game Console"));
            case ELECTRONICS -> types.addAll(List.of("Computer", "Home Appliances"));
            default -> throw new IllegalStateException("Unexpected value: " + store.getType());
        }
        System.out.print("Insert type: ");
        String stringType = CLIReader.multiChoiceString(types);
        return ProductType.valueOf(stringType.toUpperCase().replace(" ", ""));
    }

    public String getInformation(String name) throws IOException {
        System.out.printf("Insert %s: ", name);
        return CLIReader.readline();
    }

    public String getChoiceInformation(String name, List<String> choices) throws IOException {
        System.out.printf("Insert %s%n", name);
        return CLIReader.multiChoiceString(choices);
    }

    public boolean getYesOrNo(String name) throws IOException {
        return CLIReader.yesOrNo(String.format("%s? [y/n]", name));
    }

    public String getImagePath() throws IOException {
        System.out.print("Insert path of the image you want to upload: ");
        return CLIReader.readline();
    }
}
