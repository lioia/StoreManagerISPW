package com.shoppingpoint.controller;

import com.shoppingpoint.dao.OfferDAO;
import com.shoppingpoint.dao.RequestDAO;
import com.shoppingpoint.exception.ControllerException;
import com.shoppingpoint.exception.DatabaseException;
import com.shoppingpoint.model.Offer;
import com.shoppingpoint.model.Request;

import java.sql.SQLException;
import java.util.List;

public class AcceptOfferController {
    private static final String DATABASE_REASON = "Database";

    public List<Request> getRequestsOfProduct(int productId) throws ControllerException {
        try {
            return RequestDAO.getRequestsOfProduct(productId);
        } catch (SQLException e) {
            throw new ControllerException("SQL", e);
        } catch (DatabaseException e) {
            throw new ControllerException(DATABASE_REASON, e);
        }
    }

    public Offer getAcceptedOffer(int requestId) throws ControllerException {
        try {
            return OfferDAO.getAcceptedOfferOfRequest(requestId);
        } catch (SQLException e) {
            throw new ControllerException("SQL", e);
        } catch (DatabaseException e) {
            throw new ControllerException(DATABASE_REASON, e);
        }
    }

    public List<Offer> getOffersOfRequest(int requestId) throws ControllerException {
        try {
            return OfferDAO.getOffersOfRequest(requestId);
        } catch (SQLException e) {
            throw new ControllerException("SQL", e);
        } catch (DatabaseException e) {
            throw new ControllerException(DATABASE_REASON, e);
        }
    }

    public void acceptOffer(int requestId, int offerId) throws ControllerException {
        try {
            RequestDAO.acceptRequest(requestId);
            OfferDAO.acceptOffer(offerId);
        } catch (SQLException e) {
            throw new ControllerException("SQL", e);
        }
    }

    public float getIncrementOfRequestPrice(float maxPrice, float offerPrice) {
        return (offerPrice / maxPrice) * 100;
    }
}
