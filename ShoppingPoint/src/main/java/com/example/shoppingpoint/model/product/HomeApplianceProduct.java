package com.example.shoppingpoint.model.product;

import com.example.shoppingpoint.utils.StatusType;

public class HomeApplianceProduct extends Product {
    private String energyClass, specs;

    public HomeApplianceProduct(String name, Number price, Number discountedPrice, Integer quantity, StatusType status, String energyClass, String specs) {
        setName(name);
        setPrice(price);
        setDiscountedPrice(discountedPrice);
        setQuantity(quantity);
        setStatus(status);
        setEnergyClass(energyClass);
        setSpecs(specs);
    }

    public String getEnergyClass() {
        return energyClass;
    }

    public void setEnergyClass(String energyClass) {
        this.energyClass = energyClass;
    }

    public String getSpecs() {
        return specs;
    }

    public void setSpecs(String specs) {
        this.specs = specs;
    }
}
