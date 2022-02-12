package com.shoppingpoint.controller;

import com.shoppingpoint.dao.LoyaltyCardDAO;
import com.shoppingpoint.exception.ControllerException;
import com.shoppingpoint.exception.DatabaseException;
import com.shoppingpoint.model.ClientListData;

import java.sql.SQLException;
import java.util.List;

public class ClientListController {
    public List<ClientListData> getClientFromStore(String storeName) throws ControllerException {
        try {
            return LoyaltyCardDAO.getClientFromStoreName(storeName);
        } catch (SQLException e) {
            throw new ControllerException("SQL", e);
        } catch (DatabaseException e) {
            throw new ControllerException("Database", e);
        }
    }
}
