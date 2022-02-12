package com.shoppingpoint.controller;

import com.shoppingpoint.dao.OfferDAO;
import com.shoppingpoint.exception.ControllerException;
import com.shoppingpoint.exception.DatabaseException;
import com.shoppingpoint.model.Offer;
import com.shoppingpoint.singleton.LoggedInUser;

import java.sql.SQLException;
import java.util.List;

public class AcceptedOfferController {
    public List<Offer> getAcceptedOffersOfSupplier() throws ControllerException {
        try {
            return OfferDAO.getAcceptedOffersOfSupplier(LoggedInUser.getInstance().getUser().getUsername());
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
