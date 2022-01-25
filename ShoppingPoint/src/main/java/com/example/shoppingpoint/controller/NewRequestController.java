package com.example.shoppingpoint.controller;

import com.example.shoppingpoint.bean.NewRequestBean;
import com.example.shoppingpoint.dao.RequestDAO;

public class NewRequestController {
    public void saveRequest(NewRequestBean bean, int productId) throws Exception {
        RequestDAO.saveNewRequest(productId, bean.getMaxPrice(), bean.getQuantity());
    }
}
