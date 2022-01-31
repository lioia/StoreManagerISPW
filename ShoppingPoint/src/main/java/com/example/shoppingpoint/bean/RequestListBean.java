package com.example.shoppingpoint.bean;

import com.example.shoppingpoint.exception.BeanException;

public class RequestListBean {
    private float offerPrice;

    public RequestListBean(String offerPrice) throws BeanException {
        setOfferPrice(offerPrice);
    }

    public void setOfferPrice(String offerPrice) throws BeanException {
        try {
            float offer = Float.parseFloat(offerPrice);
            if(offer < 0) throw new BeanException("offer price", "it has to be more than 0");
            this.offerPrice = offer;
        } catch(NumberFormatException e) {
            throw new BeanException("offer price", "invalid format");
        }
    }

    public float getOfferPrice() {
        return offerPrice;
    }
}
