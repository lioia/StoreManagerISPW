package com.example.shoppingpoint.controller;

import com.example.shoppingpoint.bean.NewRequestBean;
import com.example.shoppingpoint.dao.RequestDAO;

import java.sql.SQLException;

public class NewRequestController {
    public void saveRequest(NewRequestBean bean, int productId) throws SQLException {
        RequestDAO.saveNewRequest(productId, bean.getMaxPrice(), bean.getQuantity());
    }
}
