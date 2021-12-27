package com.example.shoppingpoint.model;

import com.example.shoppingpoint.model.product.Product;
import com.example.shoppingpoint.utils.StoreType;

import java.util.ArrayList;

public class Store {
    private String name;
    private String address;
    private Integer pointsInEuro;
    private StoreType type;
    private ArrayList<Product> products;
    private ArrayList<SoldProduct> soldProducts;

    public Store(String name, String address, Integer pointsInEuro, StoreType type, ArrayList<Product> products, ArrayList<SoldProduct> soldProducts) {
        setName(name);
        setAddress(address);
        setPointsInEuro(pointsInEuro);
        setType(type);
        setProducts(products);
        setSoldProducts(soldProducts);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getPointsInEuro() {
        return pointsInEuro;
    }

    public void setPointsInEuro(Integer pointsInEuro) {
        this.pointsInEuro = pointsInEuro;
    }

    public StoreType getType() {
        return type;
    }

    public void setType(StoreType type) {
        this.type = type;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public ArrayList<SoldProduct> getSoldProducts() {
        return soldProducts;
    }

    public void setSoldProducts(ArrayList<SoldProduct> soldProducts) {
        this.soldProducts = soldProducts;
    }
}
