package com.example.shoppingpoint.model.product;

import com.example.shoppingpoint.utils.ConsoleType;
import com.example.shoppingpoint.utils.StatusType;

public class GameConsoleProduct extends Product {
    private ConsoleType consoleType;
    private boolean digitalOnly;

    public GameConsoleProduct(Integer id, String name, Float price, Float discountedPrice, Integer quantity, StatusType status, ConsoleType consoleType, boolean digitalOnly) {
        setId(id);
        setName(name);
        setPrice(price);
        setDiscountedPrice(discountedPrice);
        setQuantity(quantity);
        setStatus(status);
        setConsoleType(consoleType);
        setDigitalOnly(digitalOnly);
    }

    public ConsoleType getConsoleType() {
        return consoleType;
    }

    public void setConsoleType(ConsoleType consoleType) {
        this.consoleType = consoleType;
    }

    public boolean isDigitalOnly() {
        return digitalOnly;
    }

    public void setDigitalOnly(boolean digitalOnly) {
        this.digitalOnly = digitalOnly;
    }
}
