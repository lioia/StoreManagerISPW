package com.example.shoppingpoint.controller;

import com.example.shoppingpoint.adapter.GenericProduct;
import com.example.shoppingpoint.dao.OfferDAO;
import com.example.shoppingpoint.dao.ProductDAO;
import com.example.shoppingpoint.dao.RequestDAO;
import com.example.shoppingpoint.model.Offer;
import com.example.shoppingpoint.model.Request;
import com.example.shoppingpoint.model.product.Product;

import java.util.List;

public class OffersController {
    public List<Request> getRequestsOfProduct(int productId) throws Exception {
        return RequestDAO.getRequestsOfProduct(productId);
    }

    public Offer getAcceptedOffer(int requestId) throws Exception {
        return OfferDAO.getAcceptedOfferOfRequest(requestId);
    }

    public List<Offer> getOffersOfRequest(int requestId) throws Exception {
        return OfferDAO.getOffersOfRequest(requestId);
    }

    public void acceptOffer(Request request, GenericProduct product, int offerId) throws Exception {
        RequestDAO.acceptRequest(request.getRequestId());
        OfferDAO.acceptOffer(offerId);
        ProductDAO.updateProduct(product.getId(), product.getPrice(), product.getDiscountedPrice(), product.getQuantity() + request.getQuantity());
    }
}
