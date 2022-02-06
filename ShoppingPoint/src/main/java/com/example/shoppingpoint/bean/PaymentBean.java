package com.example.shoppingpoint.bean;

import com.example.shoppingpoint.exception.BeanException;

public class PaymentBean {
    private int quantity;
    private boolean loyaltyCardUsed;

    public PaymentBean(int quantity, boolean loyaltyCardUsed) throws BeanException {
        setQuantity(quantity);
        setLoyaltyCardUsed(loyaltyCardUsed);
    }

    public PaymentBean(String quantity, boolean loyaltyCardUsed) throws BeanException {
        this(Integer.parseInt(quantity), loyaltyCardUsed);
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) throws BeanException {
        if (quantity < 0) throw new BeanException("quantity", "it has to be more than 0");
        this.quantity = quantity;
    }

    public boolean isLoyaltyCardUsed() {
        return loyaltyCardUsed;
    }

    public void setLoyaltyCardUsed(boolean loyaltyCardUsed) {
        this.loyaltyCardUsed = loyaltyCardUsed;
    }
}
