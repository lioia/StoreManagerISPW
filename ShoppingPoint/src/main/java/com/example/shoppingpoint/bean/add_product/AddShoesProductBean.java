package com.example.shoppingpoint.bean.add_product;

public class AddShoesProductBean {
    private String size;
    private String material;
    private String shoesType;

    public AddShoesProductBean(String size, String material, String shoesType) {
        setSize(size);
        setMaterial(material);
        setShoesType(shoesType);
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
