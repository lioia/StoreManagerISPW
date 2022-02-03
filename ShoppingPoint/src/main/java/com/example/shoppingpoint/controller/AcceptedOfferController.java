package com.example.shoppingpoint.controller;

import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.util.List;

import com.example.shoppingpoint.exception.ControllerException;
import com.example.shoppingpoint.model.Offer;
import com.example.shoppingpoint.dao.OfferDAO;
import com.example.shoppingpoint.singleton.LoggedInUser;

public class AcceptedOfferController
{
    public List<Offer> getAcceptedOffersOfSupplier()throws ControllerException{
        try {
            return OfferDAO.getAcceptedOffersOfSupplier(LoggedInUser.getInstance().getUser().getUsername());
        } catch (SQLException e) {
            throw new ControllerException("SQL", e);
        }
    }

    public Boolean IsOfferChecked(int offerId) throws ControllerException {
        try {
            return OfferDAO.isOfferChecked(offerId);
        } catch (SQLException e) {
            throw new ControllerException("SQL", e);
        }
    }
}
