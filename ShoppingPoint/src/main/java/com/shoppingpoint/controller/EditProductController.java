package com.shoppingpoint.controller;

import com.shoppingpoint.bean.store_dashboard.EditProductBean;
import com.shoppingpoint.dao.ProductDAO;
import com.shoppingpoint.exception.ControllerException;

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
