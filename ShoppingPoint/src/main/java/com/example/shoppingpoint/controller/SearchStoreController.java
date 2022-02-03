package com.example.shoppingpoint.controller;

import com.example.shoppingpoint.bean.SearchStoreBean;
import com.example.shoppingpoint.dao.StoreDAO;
import com.example.shoppingpoint.exception.ControllerException;
import com.example.shoppingpoint.model.Store;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SearchStoreController {
    public List<Store> getStores(SearchStoreBean bean) throws ControllerException {
        List<Store> stores = null;
        List<Store> filteredStores = new ArrayList<>();
        try {
            stores = StoreDAO.getAllStores();

            if (bean.getTypeFilter() == null && bean.getSearchQuery().isEmpty()) return stores;


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

        } catch (SQLException e) {
            throw new ControllerException("SQL", e);
        }
        return filteredStores;
    }
}
