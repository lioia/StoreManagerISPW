package com.example.shoppingpoint.controller;

import java.util.List;
import com.example.shoppingpoint.model.Offer;
import com.example.shoppingpoint.dao.OfferDAO;
import com.example.shoppingpoint.singleton.LoggedInUser;

public class AcceptedOfferController
{
    public List<Offer> getAcceptedOffersOfSupplier()throws Exception{
        return OfferDAO.getAcceptedOffersOfSupplier(LoggedInUser.getInstance().getUser().getUsername());
    }
}
