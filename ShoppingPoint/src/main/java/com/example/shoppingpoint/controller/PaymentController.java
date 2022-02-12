package com.example.shoppingpoint.controller;

import com.example.shoppingpoint.adapter.GenericProduct;
import com.example.shoppingpoint.adapter.ProductAdapter;
import com.example.shoppingpoint.bean.PaymentBean;
import com.example.shoppingpoint.bean.SearchStoreBean;
import com.example.shoppingpoint.bean.StoreBean;
import com.example.shoppingpoint.dao.*;
import com.example.shoppingpoint.exception.ControllerException;
import com.example.shoppingpoint.exception.DatabaseException;
import com.example.shoppingpoint.model.LoyaltyCard;
import com.example.shoppingpoint.model.Store;
import com.example.shoppingpoint.model.product.Product;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PaymentController {
    public List<Store> getStores(SearchStoreBean bean) throws ControllerException {
        List<Store> stores;
        List<Store> filteredStores = new ArrayList<>();
        try {
            stores = StoreDAO.getAllStores();

            if (bean.getTypeFilter() == null && bean.getSearchQuery().isEmpty()) return stores;

            if (bean.getTypeFilter() != null) {
                for (Store store : stores) {
                    if (store.getType() == bean.getTypeFilter()) filteredStores.add(store);
                }
            }

            if (!bean.getSearchQuery().isEmpty()) {
                filteredStores.removeIf(store -> !store.getName().toLowerCase().contains(bean.getSearchQuery().toLowerCase()));
            }

        } catch (SQLException e) {
            throw new ControllerException("SQL", e);
        }
        return filteredStores;
    }

    public List<GenericProduct> getProductsFromStore(StoreBean bean) throws ControllerException {
        List<GenericProduct> genericProducts = new ArrayList<>();
        try {
            List<Product> products = ProductDAO.getProductsFromStore(bean.getStoreName());
            List<Product> filteredProducts = new ArrayList<>();
            if (bean.getSearchQuery().isEmpty()) filteredProducts = products;
            else {
                for (Product product : products) {
                    if (product.getName().toLowerCase().contains(bean.getSearchQuery().toLowerCase()))
                        filteredProducts.add(product);
                }
            }
            for (Product product : filteredProducts) {
                genericProducts.add(new ProductAdapter(product));
            }

        } catch (SQLException e) {
            throw new ControllerException("SQL", e);
        }

        return genericProducts;
    }

    public LoyaltyCard getLoyaltyCard(String client, String storeName) throws ControllerException {
        try {
            return LoyaltyCardDAO.getLoyaltyCardFromClientAndStoreName(client, storeName);
        } catch (SQLException e) {
            throw new ControllerException("SQL", e);
        } catch (DatabaseException e) {
            throw new ControllerException("Database", e);
        }
    }

    public float buy(PaymentBean bean, LoyaltyCard card, String clientUsername, Store store, GenericProduct product) throws ControllerException {
        float fullTotal = bean.getQuantity() * product.getDiscountedPrice();
        float total = fullTotal;
        int pointsUsed = 0;
        if (bean.isLoyaltyCardUsed()) {
            pointsUsed = calculatePointsUsed(fullTotal, card.getPoints(), store.getPointsInEuro());
            total = fullTotal - (float) (pointsUsed / store.getPointsInEuro());
        }

        try {
            if (store.getPointsInEuro() != 0 && store.getEuroInPoints() != 0 && card != null) {
                int pointsToBeAdded = (int) Math.floor(total / store.getEuroInPoints());
                LoyaltyCardDAO.updateLoyaltyCard(clientUsername, store.getName(), card.getPoints() - pointsUsed + pointsToBeAdded);
            }

            ProductDAO.updateProduct(product.getId(), product.getPrice(), product.getDiscountedPrice(), product.getQuantity() - bean.getQuantity());
            int soldProductId = SoldProductDAO.saveSoldProduct(bean.getQuantity(), LocalDate.now(), product.getId(), clientUsername, store.getName());
            ReviewDAO.addReview(0f, clientUsername, soldProductId, product.getId());
            return total;
        } catch (SQLException e) {
            throw new ControllerException("SQL", e);
        } catch (DatabaseException e) {
            throw new ControllerException("Database", e);
        }
    }

    public int calculatePointsUsed(float fullTotal, int cardPoints, int pointsInEuro) {
        float total = fullTotal - (float) (cardPoints / pointsInEuro);
        if (total < 0) return (int) Math.floor(fullTotal * pointsInEuro);
        return cardPoints;
    }

    public float calculateDiscountPercentage(float price, float discountPrice) {
        return 100 - (discountPrice / price) * 100;
    }
}
