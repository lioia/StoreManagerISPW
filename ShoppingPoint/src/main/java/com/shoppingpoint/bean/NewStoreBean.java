package com.shoppingpoint.bean;

import com.shoppingpoint.exception.BeanException;
import com.shoppingpoint.utils.StoreType;

public class NewStoreBean {
    private String name;
    private String address;
    private StoreType storeType;

    public NewStoreBean(String name, String address, String type) throws BeanException {
        setName(name);
        setAddress(address);
        setStoreType(type);
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public StoreType getStoreType() {
        return storeType;
    }


    public void setName(String name) throws BeanException {
        if (name.length() < 4) throw new BeanException("store name", "it has to be longer than 4 characters");
        if (name.length() > 30) throw new BeanException("store name", "it has to be shorter than 30 characters");
        this.name = name;
    }

    public void setAddress(String address) throws BeanException {
        if (address.length() < 8) throw new BeanException("address", "it has to be longer than 8 characters");
        if (address.length() > 40) throw new BeanException("address", "it has to be shorter than 40 characters");
        this.address = address;
    }

    public void setStoreType(String type) {
        switch (type) {
            case "Clothes" -> this.storeType = StoreType.CLOTHES;
            case "Books" -> this.storeType = StoreType.BOOKS;
            case "VideoGames" -> this.storeType = StoreType.VIDEOGAMES;
            case "Electronics" -> this.storeType = StoreType.ELECTRONICS;
            default -> throw new IllegalStateException("Unexpected value: " + type);
        }
    }
}
