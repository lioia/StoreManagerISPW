package com.example.shoppingpoint.controller;

import com.example.shoppingpoint.bean.SearchStoreBean;
import com.example.shoppingpoint.dao.StoreDAO;
import com.example.shoppingpoint.model.Store;

import java.util.List;

public class SearchStoreController {
    public List<Store> getStores(SearchStoreBean bean) throws Exception {
        List<Store> stores = StoreDAO.getAllStores();

        if (bean.getTypeFilter() != null) {
            stores.removeIf(store -> store.getType() != bean.getTypeFilter());
        }

        if (!bean.getSearchQuery().isEmpty()) {
            stores.removeIf(store -> !store.getName().contains(bean.getSearchQuery()));
        }

        return stores;
    }
}
