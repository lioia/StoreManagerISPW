package com.shoppingpoint.controller;

import com.shoppingpoint.bean.RequestListBean;
import com.shoppingpoint.dao.OfferDAO;
import com.shoppingpoint.dao.ProductDAO;
import com.shoppingpoint.dao.RequestDAO;
import com.shoppingpoint.exception.ControllerException;
import com.shoppingpoint.exception.DatabaseException;
import com.shoppingpoint.model.Request;
import com.shoppingpoint.model.product.Product;
import com.shoppingpoint.singleton.LoggedInUser;

import java.sql.SQLException;
import java.util.List;

public class MakeOfferController {
    public List<Request> getRequest() throws ControllerException {
        try {
            return RequestDAO.getAllRequestsNotAccepted();
        } catch (SQLException e) {
            throw new ControllerException("SQL", e);
        } catch (DatabaseException e) {
            throw new ControllerException("Database", e);
        }
    }

    public Product getProduct(int productId) throws ControllerException {
        try {
            return ProductDAO.getProductById(productId);
        } catch (SQLException e) {
            throw new ControllerException("SQL", e);
        } catch (DatabaseException e) {
            throw new ControllerException("Database", e);
        }
    }

    public void saveOffer(int requestId, RequestListBean offerPrice) throws ControllerException {
        try {
            OfferDAO.saveOffer(LoggedInUser.getInstance().getUser().getUsername(), requestId, offerPrice.getOfferPrice());
        } catch (SQLException e) {
            throw new ControllerException("SQL", e);
        }
    }

    public void checkedOffer() throws ControllerException {
        try {
            OfferDAO.checkedOffer(LoggedInUser.getInstance().getUser().getUsername());
        } catch (SQLException e) {
            throw new ControllerException("SQL", e);
        }
    }

    public int countAcceptedOffers() throws ControllerException {
        try {
            return OfferDAO.countAcceptedOffer(LoggedInUser.getInstance().getUser().getUsername());
        } catch (SQLException e) {
            throw new ControllerException("SQL", e);
        }
    }
}
