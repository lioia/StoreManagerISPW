package com.example.shoppingpoint.model;

import com.example.shoppingpoint.model.product.Product;
import com.example.shoppingpoint.utils.StoreType;

import java.util.ArrayList;
import java.util.List;

public class Store {
    private String name;
    private String address;
    private Integer pointsInEuro;
    private Integer euroInPoints;
    private StoreType type;
    private List<Product> products;
    private List<SoldProduct> soldProducts;

    public Store(String name, String address, Integer pointsInEuro, Integer euroInPoints, StoreType type) {
        this(name, address, pointsInEuro, euroInPoints, type, new ArrayList<>(), new ArrayList<>());
    }

    public Store(String name, String address, Integer pointsInEuro, Integer euroInPoints, StoreType type, List<Product> products, List<SoldProduct> soldProducts) {
        setName(name);
        setAddress(address);
        setPointsInEuro(pointsInEuro);
        setEuroInPoints(euroInPoints);
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

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<SoldProduct> getSoldProducts() {
        return soldProducts;
    }

    public void setSoldProducts(List<SoldProduct> soldProducts) {
        this.soldProducts = soldProducts;
    }

    public Integer getEuroInPoints() {
        return euroInPoints;
    }

    public void setEuroInPoints(Integer euroInPoints) {
        this.euroInPoints = euroInPoints;
    }
}
