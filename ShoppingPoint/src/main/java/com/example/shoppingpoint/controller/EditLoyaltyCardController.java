package com.example.shoppingpoint.controller;

import com.example.shoppingpoint.bean.store_dashboard.LoyaltyCardBean;
import com.example.shoppingpoint.dao.StoreDAO;
import com.example.shoppingpoint.exception.ControllerException;
import com.example.shoppingpoint.model.Store;

import java.sql.SQLException;

public class EditLoyaltyCardController {
    public void updateLoyaltyCard(LoyaltyCardBean bean, Store store) throws ControllerException {
        try {
            StoreDAO.updatePoints(bean.getPointsInEuro(), bean.getEuroInPoints(), store.getName());
        } catch (SQLException e) {
            throw new ControllerException("SQL", e);
        }
    }

    public void disableLoyaltyCard(Store store) throws ControllerException {
        try {
            StoreDAO.updatePoints(0, 0, store.getName());
        } catch(SQLException e) {
            throw new ControllerException("SQL", e);
        }
    }
}
