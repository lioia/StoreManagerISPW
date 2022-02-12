package com.shoppingpoint.bean;

import com.shoppingpoint.utils.StoreType;

public class SearchStoreBean {
    private StoreType typeFilter;
    private String searchQuery;

    public SearchStoreBean() {
        this("All", "");
    }

    public SearchStoreBean(String filter, String query) {
        setTypeFilter(filter);
        setSearchQuery(query);
    }

    public StoreType getTypeFilter() {
        return typeFilter;
    }

    public void setTypeFilter(String typeFilter) {
        switch (typeFilter) {
            case "Clothes" -> this.typeFilter = StoreType.CLOTHES;
            case "Books" -> this.typeFilter = StoreType.BOOKS;
            case "Video Games" -> this.typeFilter = StoreType.VIDEOGAMES;
            case "Electronics" -> this.typeFilter = StoreType.ELECTRONICS;
            default -> this.typeFilter = null; // All
        }
    }

    public void setTypeFilter(StoreType typeFilter) {
        this.typeFilter = typeFilter;
    }

    public String getSearchQuery() {
        return searchQuery;
    }

    public void setSearchQuery(String searchQuery) {
        this.searchQuery = searchQuery;
    }
}
