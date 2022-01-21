package com.example.shoppingpoint.controller;

import com.example.shoppingpoint.adapter.GenericProduct;
import com.example.shoppingpoint.bean.PaymentBean;
import com.example.shoppingpoint.dao.LoyaltyCardDAO;
import com.example.shoppingpoint.dao.ProductDAO;
import com.example.shoppingpoint.dao.SoldProductDAO;
import com.example.shoppingpoint.model.LoyaltyCard;
import com.example.shoppingpoint.model.Store;

import java.time.LocalDate;

public class PaymentController {
    public void buy(PaymentBean bean, LoyaltyCard card, String clientUsername, Store store, GenericProduct product) throws Exception {
        float total = bean.getQuantity() * product.getDiscountedPrice();
        int pointsUsed = 0;
        int pointsToBeAdded = (int) (total / store.getEuroInPoints());
        if (bean.isLoyaltyCardUsed()) {
            pointsUsed = card.getPoints();
            total = bean.getQuantity() * product.getDiscountedPrice() - (float) pointsUsed / store.getPointsInEuro();
            if (total < 0) {
                pointsUsed = (int) (product.getDiscountedPrice() * store.getPointsInEuro());
                pointsToBeAdded = 0;
            } else {
                pointsToBeAdded = (int) (total / store.getEuroInPoints());
            }
        }

        if (store.getPointsInEuro() != 0 && store.getEuroInPoints() != 0)
            LoyaltyCardDAO.updateLoyaltyCard(clientUsername, store.getName(), card.getPoints() - pointsUsed + pointsToBeAdded);

        ProductDAO.updateProductQuantity(product.getId(), product.getQuantity() - bean.getQuantity());
        SoldProductDAO.saveSoldProduct(bean.getQuantity(), LocalDate.now(), product.getId(), clientUsername);
    }
}
