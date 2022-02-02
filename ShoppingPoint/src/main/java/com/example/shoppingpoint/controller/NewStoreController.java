package com.example.shoppingpoint.controller;

import com.example.shoppingpoint.bean.NewStoreBean;
import com.example.shoppingpoint.dao.StoreDAO;
import com.example.shoppingpoint.exception.DatabaseException;
import com.example.shoppingpoint.model.Store;

import java.sql.SQLException;

public class NewStoreController {
    public Store register(NewStoreBean bean, String storeOwner) throws SQLException, DatabaseException {
        StoreDAO.saveStore(bean.getName(), bean.getAddress(), bean.getStoreType(), storeOwner);
        return StoreDAO.getStoreByName(bean.getName());
    }
}
