package com.example.shoppingpoint.bean;

import com.example.shoppingpoint.utils.StoreType;
import com.example.shoppingpoint.utils.UserType;

public class NewStoreBean {
    private String name;
    private String address;
    private StoreType storeType;

    public NewStoreBean(){}

    public NewStoreBean(String name, String address, String type){
        setName(name);
        setAddress(address);
        setStoreType(type);
    }

    public String getName() {return name;}
    public String getAddress() {return address;}
    public StoreType getStoreType() {return storeType;}


    // TODO: bean exception
    public void setName(String name) {
        if (name.length() < 4 || name.length() > 30) {
//            TODO throws exception
            System.out.println("Store name not valid");
        }
        this.name = name;
    }

    public void setAddress(String address) {
        if(address.length() < 8 || address.length() > 40) {
//            TODO throws exception
            System.out.println("address not valid");
        }
        this.address = address;
    }

    public void setStoreType(String type){
        switch (type) {
            case "Clothes" -> this.storeType = StoreType.CLOTHES;
            case "Books" -> this.storeType = StoreType.BOOKS;
            case "VideoGames" -> this.storeType = StoreType.VIDEOGAMES;
            default -> this.storeType = StoreType.ELECTRONICS; // case "Electronics"
        }
    }
}
