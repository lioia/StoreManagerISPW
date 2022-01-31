package com.example.shoppingpoint.model;

import com.example.shoppingpoint.model.user.Supplier;

public class Offer {
    private int offerId;
    private int requestId;
    private float offerPrice;
    private boolean accepted;
    private String supplierUsername;

    public Offer(int offerId, int requestId, float offerPrice, boolean accepted, String supplierUsername) {
        setOfferId(offerId);
        setRequestId(requestId);
        setOfferPrice(offerPrice);
        setAccepted(accepted);
        setSupplierUsername(supplierUsername);
    }

    public int getOfferId() {
        return offerId;
    }

    public void setOfferId(int offerId) {
        this.offerId = offerId;
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public float getOfferPrice() {
        return offerPrice;
    }

    public void setOfferPrice(float offerPrice) {
        this.offerPrice = offerPrice;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }


    public String getSupplierUsername() {
        return supplierUsername;
    }

    public void setSupplierUsername(String supplierUsername) {
        this.supplierUsername = supplierUsername;
    }
}
