package com.example.shoppingpoint.controller;

import com.example.shoppingpoint.bean.store_dashboard.EditProductBean;
import com.example.shoppingpoint.dao.ProductDAO;
import com.example.shoppingpoint.exception.ControllerException;

import java.sql.SQLException;

public class EditProductController {
    public void editProduct(Integer id, EditProductBean bean) throws ControllerException {
        try {
            ProductDAO.updateProduct(id, bean.getNewPrice(), bean.getNewDiscountedPrice(), bean.getNewQuantity());
        } catch (SQLException e) {
            throw new ControllerException("SQL", e);
        }
    }
}
