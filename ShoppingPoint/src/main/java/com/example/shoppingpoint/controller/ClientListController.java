package com.example.shoppingpoint.controller;

import com.example.shoppingpoint.exception.ControllerException;
import com.example.shoppingpoint.exception.DatabaseException;
import com.example.shoppingpoint.model.ClientListData;
import com.example.shoppingpoint.dao.LoyaltyCardDAO;

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
