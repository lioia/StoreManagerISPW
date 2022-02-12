package com.shoppingpoint.model.product;

import com.shoppingpoint.utils.ProductType;

public class ClothesProduct extends Product {
    private String size;
    private String material;

    public ClothesProduct(Integer id, String name, Float price, Float discountedPrice, Integer quantity, String status, String storeName, String size, String material) {
        setId(id);
        setName(name);
        setPrice(price);
        setDiscountedPrice(discountedPrice);
        setQuantity(quantity);
        setStatus(status);
        setStoreName(storeName);
        setClothesSize(size);
        setClothesMaterial(material);
        setType(ProductType.CLOTHES);
    }

    public String getClothesSize() {
        return size;
    }

    public void setClothesSize(String size) {
        this.size = size;
    }

    public String getClothesMaterial() {
        return material;
    }

    public void setClothesMaterial(String material) {
        this.material = material;
    }
}
