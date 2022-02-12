package com.shoppingpoint.model;

public class Offer {
    private int offerId;
    private Request request;
    private float offerPrice;
    private boolean accepted;
    private String supplierUsername;
    private boolean checked;

    public Offer(int offerId, Request request, float offerPrice, boolean accepted, String supplierUsername, boolean checked) {
        setOfferId(offerId);
        setRequest(request);
        setOfferPrice(offerPrice);
        setAccepted(accepted);
        setSupplierUsername(supplierUsername);
        setChecked(checked);
    }

    public int getOfferId() {
        return offerId;
    }

    public void setOfferId(int offerId) {
        this.offerId = offerId;
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

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
