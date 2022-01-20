package com.example.shoppingpoint.bean;

public class PaymentBean {
    private Integer quantity;
    private boolean loyaltyCardUsed;

    public PaymentBean(String quantity, boolean loyaltyCardUsed) {
        setQuantity(quantity);
        setLoyaltyCardUsed(loyaltyCardUsed);
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = Integer.parseInt(quantity);
    }

    public boolean isLoyaltyCardUsed() {
        return loyaltyCardUsed;
    }

    public void setLoyaltyCardUsed(boolean loyaltyCardUsed) {
        this.loyaltyCardUsed = loyaltyCardUsed;
    }
}
