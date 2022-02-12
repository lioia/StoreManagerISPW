package com.shoppingpoint.model.user;

import com.shoppingpoint.model.Store;

public class StoreOwner extends User {
    private Store store;

    public StoreOwner(String username, String email, String password) {
        this(username, email, password, null);
    }

    public StoreOwner(String username, String email, String password, Store store) {
        setUsername(username);
        setEmail(email);
        setPassword(password);
        setStore(store);
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }
}
