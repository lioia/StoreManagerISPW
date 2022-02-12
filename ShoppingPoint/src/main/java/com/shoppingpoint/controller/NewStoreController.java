package com.shoppingpoint.controller;

import com.shoppingpoint.bean.NewStoreBean;
import com.shoppingpoint.dao.StoreDAO;
import com.shoppingpoint.exception.ControllerException;
import com.shoppingpoint.exception.DatabaseException;
import com.shoppingpoint.model.Store;

import java.sql.SQLException;

public class NewStoreController {
    public Store register(NewStoreBean bean, String storeOwner) throws ControllerException {
        try {
            StoreDAO.saveStore(bean.getName(), bean.getAddress(), bean.getStoreType(), storeOwner);
            return StoreDAO.getStoreByName(bean.getName());
        } catch (SQLException e) {
            throw new ControllerException("SQL", e);
        } catch (DatabaseException e) {
            throw new ControllerException("Database", e);
        }
    }
}
