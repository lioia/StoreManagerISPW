package com.example.shoppingpoint.model.product;

import com.example.shoppingpoint.utils.ProductType;

public class GameConsoleProduct extends Product {
    private String consoleType;
    private boolean digitalOnly;

    public GameConsoleProduct(Integer id, String name, Float price, Float discountedPrice, Integer quantity, String status, String storeName, String consoleType, boolean digitalOnly) {
        setId(id);
        setName(name);
        setPrice(price);
        setDiscountedPrice(discountedPrice);
        setQuantity(quantity);
        setStatus(status);
        setStoreName(storeName);
        setConsoleType(consoleType);
        setDigitalOnly(digitalOnly);
        setType(ProductType.GAMECONSOLE);
    }

    public String getConsoleType() {
        return consoleType;
    }

    public void setConsoleType(String consoleType) {
        this.consoleType = consoleType;
    }

    public boolean isDigitalOnly() {
        return digitalOnly;
    }

    public void setDigitalOnly(boolean digitalOnly) {
        this.digitalOnly = digitalOnly;
    }
}
