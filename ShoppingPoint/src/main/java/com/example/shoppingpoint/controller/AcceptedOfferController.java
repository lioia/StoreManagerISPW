package com.example.shoppingpoint.controller;

import java.sql.SQLException;
import java.util.List;

import com.example.shoppingpoint.dao.RequestDAO;
import com.example.shoppingpoint.exception.ControllerException;
import com.example.shoppingpoint.exception.DatabaseException;
import com.example.shoppingpoint.exception.ControllerException;
import com.example.shoppingpoint.model.Offer;
import com.example.shoppingpoint.dao.OfferDAO;
import com.example.shoppingpoint.model.Request;
import com.example.shoppingpoint.singleton.LoggedInUser;

public class AcceptedOfferController {
    public List<Offer> getAcceptedOffersOfSupplier() throws ControllerException {
        try {
            return OfferDAO.getAcceptedOffersOfSupplier(LoggedInUser.getInstance().getUser().getUsername());
        } catch (SQLException e) {
            throw new ControllerException("SQL", e);
        }
    }

    public Request getRequestById(int requestId) throws ControllerException {
        try {
            return RequestDAO.getRequestById(requestId);
        } catch (SQLException e) {
            throw new ControllerException("SQL", e);
        } catch (DatabaseException e) {
            throw new ControllerException("Database", e);
        }
    }

    public Boolean isOfferChecked(int offerId) throws ControllerException {
        try {
            return OfferDAO.isOfferChecked(offerId);
        } catch (SQLException e) {
            throw new ControllerException("SQL", e);
        }
    }
}
