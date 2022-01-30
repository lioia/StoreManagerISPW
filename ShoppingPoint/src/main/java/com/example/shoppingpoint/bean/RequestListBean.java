package com.example.shoppingpoint.bean;

public class RequestListBean {
    private float offerPrice;
    public RequestListBean(String offerPrice)throws Exception{
        setOfferPrice(offerPrice);
    }

    public void setOfferPrice(String offerPrice) throws Exception{
        float offerPrice1=Float.parseFloat(offerPrice);
        if(offerPrice1 < 0 ) {
//            TODO exception
            System.out.println("Invalid data");
        }
        this.offerPrice = offerPrice1;
    }

    public float getOfferPrice() {
        return offerPrice;
    }
}
