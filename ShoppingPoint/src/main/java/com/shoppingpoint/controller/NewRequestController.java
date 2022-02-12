package com.shoppingpoint.controller;

import com.shoppingpoint.bean.NewRequestBean;
import com.shoppingpoint.dao.RequestDAO;
import com.shoppingpoint.exception.ControllerException;

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
