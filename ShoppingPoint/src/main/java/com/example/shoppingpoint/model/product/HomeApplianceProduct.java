package com.example.shoppingpoint.model.product;

import com.example.shoppingpoint.utils.ProductType;

public class HomeApplianceProduct extends Product {
    private String energyClass;
    private String specs;

    public HomeApplianceProduct(Integer id, String name, Float price, Float discountedPrice, Integer quantity, String status, String storeName, String energyClass, String specs) {
        setId(id);
        setName(name);
        setPrice(price);
        setDiscountedPrice(discountedPrice);
        setQuantity(quantity);
        setStatus(status);
        setStoreName(storeName);
        setHomeAppliancesEnergyClass(energyClass);
        setHomeAppliancesSpecs(specs);
        setType(ProductType.HOMEAPPLIANCES);
    }

    public String getHomeAppliancesEnergyClass() {
        return energyClass;
    }

    public void setHomeAppliancesEnergyClass(String energyClass) {
        this.energyClass = energyClass;
    }

    public String getHomeAppliancesSpecs() {
        return specs;
    }

    public void setHomeAppliancesSpecs(String specs) {
        this.specs = specs;
    }
}
