package com.example.shoppingpoint.bean;

import com.example.shoppingpoint.exception.BeanException;

public class PaymentBean {
    private Integer quantity;
    private boolean loyaltyCardUsed;

    public PaymentBean(String quantity, boolean loyaltyCardUsed) throws BeanException {
        setQuantity(quantity);
        setLoyaltyCardUsed(loyaltyCardUsed);
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) throws BeanException {
        try {
            int q = Integer.parseInt(quantity);
            if (q < 0) throw new BeanException("quantity", "it has to be more than 0");
            this.quantity = q;
        } catch (NumberFormatException e) {
            throw new BeanException("quantity", "invalid format");
        }
    }

    public boolean isLoyaltyCardUsed() {
        return loyaltyCardUsed;
    }

    public void setLoyaltyCardUsed(boolean loyaltyCardUsed) {
        this.loyaltyCardUsed = loyaltyCardUsed;
    }
}
