package com.shoppingpoint.bean;

public class StoreBean {
    private String searchQuery;
    private String storeName;

    public StoreBean(String storeName) {
        this(storeName, "");
    }

    public StoreBean(String storeName, String searchQuery) {
        setStoreName(storeName);
        setSearchQuery(searchQuery);
    }

    public String getSearchQuery() {
        return searchQuery;
    }

    public void setSearchQuery(String searchQuery) {
        this.searchQuery = searchQuery;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }
}
