package com.example.shoppingpoint.controller;

import com.example.shoppingpoint.bean.NewStoreBean;
import com.example.shoppingpoint.dao.StoreDAO;
import com.example.shoppingpoint.exception.ControllerException;
import com.example.shoppingpoint.exception.DatabaseException;
import com.example.shoppingpoint.model.Store;
import javafx.scene.control.Control;

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
