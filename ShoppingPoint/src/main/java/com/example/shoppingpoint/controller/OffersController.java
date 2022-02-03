package com.example.shoppingpoint.controller;

import com.example.shoppingpoint.adapter.GenericProduct;
import com.example.shoppingpoint.dao.OfferDAO;
import com.example.shoppingpoint.dao.ProductDAO;
import com.example.shoppingpoint.dao.RequestDAO;
import com.example.shoppingpoint.exception.ControllerException;
import com.example.shoppingpoint.exception.DatabaseException;
import com.example.shoppingpoint.model.Offer;
import com.example.shoppingpoint.model.Request;
import com.example.shoppingpoint.model.product.Product;
import com.example.shoppingpoint.singleton.LoggedInUser;

import java.sql.SQLException;
import java.util.List;

public class OffersController {
    public List<Request> getRequestsOfProduct(int productId) throws ControllerException {
        try {
            return RequestDAO.getRequestsOfProduct(productId);
        } catch (SQLException e) {
            throw new ControllerException("SQL", e);
        }
    }

    public Offer getAcceptedOffer(int requestId) throws ControllerException {
        try {
            return OfferDAO.getAcceptedOfferOfRequest(requestId);
        } catch (SQLException e) {
            throw new ControllerException("SQL", e);
        } catch (DatabaseException e) {
            throw new ControllerException("Database", e);
        }
    }

    public List<Offer> getOffersOfRequest(int requestId) throws ControllerException {
        try {
            return OfferDAO.getOffersOfRequest(requestId);
        } catch (SQLException e) {
            throw new ControllerException("SQL", e);
        }
    }

    public void acceptOffer(Request request, int offerId) throws ControllerException {
        try {
            RequestDAO.acceptRequest(request.getRequestId());
            OfferDAO.acceptOffer(offerId);
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
