package com.example.shoppingpoint.controller;

import com.example.shoppingpoint.adapter.GenericProduct;
import com.example.shoppingpoint.adapter.ProductAdapter;
import com.example.shoppingpoint.bean.StoreBean;
import com.example.shoppingpoint.dao.LoyaltyCardDAO;
import com.example.shoppingpoint.dao.ProductDAO;
import com.example.shoppingpoint.exception.ControllerException;
import com.example.shoppingpoint.exception.DatabaseException;
import com.example.shoppingpoint.model.LoyaltyCard;
import com.example.shoppingpoint.model.product.Product;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StoreController {
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

    public LoyaltyCard createLoyaltyCard(String client, String storeName) throws ControllerException {
        try {
            LoyaltyCardDAO.saveLoyaltyCard(client, storeName, 0);
        } catch (SQLException e) {
            throw new ControllerException("SQL", e);
        }
        return new LoyaltyCard(0,  storeName);
    }
}
