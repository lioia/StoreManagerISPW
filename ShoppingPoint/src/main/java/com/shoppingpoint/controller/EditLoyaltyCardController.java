package com.shoppingpoint.controller;

import com.shoppingpoint.bean.store_dashboard.LoyaltyCardBean;
import com.shoppingpoint.dao.StoreDAO;
import com.shoppingpoint.exception.ControllerException;
import com.shoppingpoint.model.Store;

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
