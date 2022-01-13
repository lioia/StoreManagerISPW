package com.example.shoppingpoint.model.product;

import com.example.shoppingpoint.utils.ProductType;
import com.example.shoppingpoint.utils.StatusType;

public class ShoesProduct extends Product {
    private String size;
    private String material;
    private String shoesType;

    public ShoesProduct(Integer id, String name, Float price, Float discountedPrice, Integer quantity, StatusType status, String size, String material, String shoesType) {
        setId(id);
        setName(name);
        setPrice(price);
        setDiscountedPrice(discountedPrice);
        setQuantity(quantity);
        setStatus(status);
        setSize(size);
        setMaterial(material);
        setShoesType(shoesType);
        setType(ProductType.SHOES);
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getShoesType() {
        return shoesType;
    }

    public void setShoesType(String shoesType) {
        this.shoesType = shoesType;
    }
}
