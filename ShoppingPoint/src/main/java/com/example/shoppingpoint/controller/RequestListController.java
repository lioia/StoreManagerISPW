package com.example.shoppingpoint.controller;

import java.sql.SQLException;
import java.util.List;

import com.example.shoppingpoint.bean.RequestListBean;
import com.example.shoppingpoint.exception.DatabaseException;
import com.example.shoppingpoint.model.Request;
import com.example.shoppingpoint.dao.RequestDAO;
import com.example.shoppingpoint.model.product.Product;
import com.example.shoppingpoint.dao.ProductDAO;
import com.example.shoppingpoint.dao.OfferDAO;
import com.example.shoppingpoint.singleton.LoggedInUser;

public class RequestListController {
    public List<Request> getRequest() throws SQLException {
        return RequestDAO.getAllRequestsNotAccepted();
    }

    public Product getProduct(int productId) throws SQLException, DatabaseException {
        return ProductDAO.getProductById(productId);
    }

    public void saveOffer(int requestId, RequestListBean offerPrice) throws SQLException {
        OfferDAO.saveOffer(LoggedInUser.getInstance().getUser().getUsername(), requestId, offerPrice.getOfferPrice());
    }

    public void checkedOffer() throws SQLException {
        OfferDAO.checkedOffer(LoggedInUser.getInstance().getUser().getUsername());
    }
}
