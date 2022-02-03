package com.example.shoppingpoint.controller;

import com.example.shoppingpoint.adapter.GenericProduct;
import com.example.shoppingpoint.adapter.ProductAdapter;
import com.example.shoppingpoint.bean.StoreBean;
import com.example.shoppingpoint.bean.store_dashboard.EditProductBean;
import com.example.shoppingpoint.bean.store_dashboard.LoyaltyCardBean;
import com.example.shoppingpoint.dao.LoyaltyCardDAO;
import com.example.shoppingpoint.dao.ProductDAO;
import com.example.shoppingpoint.dao.ReviewDAO;
import com.example.shoppingpoint.dao.StoreDAO;
import com.example.shoppingpoint.exception.ControllerException;
import com.example.shoppingpoint.exception.DatabaseException;
import com.example.shoppingpoint.model.Review;
import com.example.shoppingpoint.model.product.Product;
import com.example.shoppingpoint.model.Store;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StoreDashboardController {
    public Store getStoreFromStoreOwnerName(String storeOwner) throws ControllerException {
        try {
            return StoreDAO.getStoreByStoreOwnerUsername(storeOwner);
        } catch (SQLException e) {
            throw new ControllerException("SQL", e);
        } catch (DatabaseException e) {
            throw new ControllerException("Database", e);
        }
    }

    public List<GenericProduct> getProductsFromStore(Store store) throws ControllerException {
        List<Product> products;
        try {
            products = ProductDAO.getProductsFromStore(store.getName());
        } catch (SQLException e) {
            throw new ControllerException("SQL", e);
        }

        List<GenericProduct> genericProducts = new ArrayList<>();
        for (Product product : products) {
            genericProducts.add(new ProductAdapter(product));
        }

        return genericProducts;
    }

    public void updateLoyaltyCard(LoyaltyCardBean bean, Store store) throws ControllerException {
        try {
            StoreDAO.updatePoints(bean.getPointsInEuro(), bean.getEuroInPoints(), store.getName());
        } catch (SQLException e) {
            throw new ControllerException("SQL", e);
        }
    }

    public void editProduct(Integer id, EditProductBean bean) throws ControllerException {
        try {
            ProductDAO.updateProduct(id, bean.getNewPrice(), bean.getNewDiscountedPrice(), bean.getNewQuantity());
        } catch (SQLException e) {
            throw new ControllerException("SQL", e);
        }
    }
}
