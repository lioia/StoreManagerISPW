package com.example.shoppingpoint.controller;

import com.example.shoppingpoint.bean.SearchStoreBean;
import com.example.shoppingpoint.dao.StoreDAO;
import com.example.shoppingpoint.model.Store;

import java.util.ArrayList;
import java.util.List;

public class SearchStoreController {
    public List<Store> getStores(SearchStoreBean bean) throws Exception {
        List<Store> stores = StoreDAO.getAllStores();

        if (bean.getTypeFilter() == null && bean.getSearchQuery().isEmpty()) return stores;

        List<Store> filteredStores = new ArrayList<>();

        if (bean.getTypeFilter() != null) {
            for (Store store : stores) {
                if (store.getType() == bean.getTypeFilter()) filteredStores.add(store);
            }
        }

        if (!bean.getSearchQuery().isEmpty()) {
            for (Store store : stores) {
                if (store.getName().toLowerCase().contains(bean.getSearchQuery().toLowerCase()))
                    filteredStores.add(store);
            }
        }

        return filteredStores;
    }
}
