package com.example.shoppingpoint.model.product;

import com.example.shoppingpoint.utils.ProductType;

public class HomeApplianceProduct extends Product {
    private String energyClass;
    private String specs;

    public HomeApplianceProduct(Integer id, String name, Float price, Float discountedPrice, Integer quantity, String status, String energyClass, String specs) {
        setId(id);
        setName(name);
        setPrice(price);
        setDiscountedPrice(discountedPrice);
        setQuantity(quantity);
        setStatus(status);
        setEnergyClass(energyClass);
        setSpecs(specs);
        setType(ProductType.HOMEAPPLIANCES);
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
