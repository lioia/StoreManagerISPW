package com.example.shoppingpoint.controller;

import com.example.shoppingpoint.adapter.GenericProduct;
import com.example.shoppingpoint.dao.OfferDAO;
import com.example.shoppingpoint.dao.ProductDAO;
import com.example.shoppingpoint.dao.RequestDAO;
import com.example.shoppingpoint.exception.DatabaseException;
import com.example.shoppingpoint.model.Offer;
import com.example.shoppingpoint.model.Request;
import com.example.shoppingpoint.model.product.Product;

import java.sql.SQLException;
import java.util.List;

public class OffersController {
    public List<Request> getRequestsOfProduct(int productId) throws SQLException {
        return RequestDAO.getRequestsOfProduct(productId);
    }

    public Offer getAcceptedOffer(int requestId) throws SQLException, DatabaseException {
        return OfferDAO.getAcceptedOfferOfRequest(requestId);
    }

    public List<Offer> getOffersOfRequest(int requestId) throws SQLException {
        return OfferDAO.getOffersOfRequest(requestId);
    }

    public void acceptOffer(Request request,  int offerId) throws SQLException {
        RequestDAO.acceptRequest(request.getRequestId());
        OfferDAO.acceptOffer(offerId);
    }
}
