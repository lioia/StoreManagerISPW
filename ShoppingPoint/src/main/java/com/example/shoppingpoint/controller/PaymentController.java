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
                    if (product.getName().toLowerCase().contains(bean.getSearchQuery().toLowerCase())) {
                        filteredProducts.add(product);
                    }
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

    public void buy(PaymentBean bean, LoyaltyCard card, String clientUsername, Store store, GenericProduct product) throws ControllerException {
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

        try {
            if (store.getPointsInEuro() != 0 && store.getEuroInPoints() != 0 && card != null)
                LoyaltyCardDAO.updateLoyaltyCard(clientUsername, store.getName(), card.getPoints() - pointsUsed + pointsToBeAdded);

            ProductDAO.updateProduct(product.getId(), product.getPrice(), product.getDiscountedPrice(), product.getQuantity() - bean.getQuantity());
            int soldProductId = SoldProductDAO.saveSoldProduct(bean.getQuantity(), LocalDate.now(), product.getId(), clientUsername, store.getName());
            ReviewDAO.addReview(0f, clientUsername, soldProductId,product.getId());
        } catch (SQLException e) {
            throw new ControllerException("SQL", e);
        }
    }
}
