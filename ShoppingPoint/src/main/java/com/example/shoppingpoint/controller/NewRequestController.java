package com.example.shoppingpoint.controller;

import com.example.shoppingpoint.bean.NewRequestBean;
import com.example.shoppingpoint.dao.RequestDAO;
import com.example.shoppingpoint.exception.ControllerException;

import java.sql.SQLException;

public class NewRequestController {
    public void saveRequest(NewRequestBean bean, int productId) throws ControllerException {
        try {
            RequestDAO.saveNewRequest(productId, bean.getMaxPrice(), bean.getQuantity());
        } catch (SQLException e) {
            throw new ControllerException("SQL", e);
        }
    }
}
