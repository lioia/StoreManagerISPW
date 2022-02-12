package com.shoppingpoint.model.product;

import com.shoppingpoint.utils.ProductType;

public class ShoesProduct extends Product {
    private String size;
    private String material;
    private String shoesType;

    public ShoesProduct(Integer id, String name, Float price, Float discountedPrice, Integer quantity, String status, String storeName, String size, String material, String shoesType) {
        setId(id);
        setName(name);
        setPrice(price);
        setDiscountedPrice(discountedPrice);
        setQuantity(quantity);
        setStatus(status);
        setStoreName(storeName);
        setShoesSize(size);
        setShoesMaterial(material);
        setShoesType(shoesType);
        setType(ProductType.SHOES);
    }

    public String getShoesSize() {
        return size;
    }

    public void setShoesSize(String size) {
        this.size = size;
    }

    public String getShoesMaterial() {
        return material;
    }

    public void setShoesMaterial(String material) {
        this.material = material;
    }

    public String getShoesType() {
        return shoesType;
    }

    public void setShoesType(String shoesType) {
        this.shoesType = shoesType;
    }
}
