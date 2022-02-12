package com.shoppingpoint.model.product;

import com.shoppingpoint.utils.ProductType;

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
        setGameConsoleType(consoleType);
        setGameConsoleDigitalOnly(digitalOnly);
        setType(ProductType.GAMECONSOLE);
    }

    public String getGameConsoleType() {
        return consoleType;
    }

    public void setGameConsoleType(String consoleType) {
        this.consoleType = consoleType;
    }

    public boolean isDigitalOnly() {
        return digitalOnly;
    }

    public void setGameConsoleDigitalOnly(boolean digitalOnly) {
        this.digitalOnly = digitalOnly;
    }
}
