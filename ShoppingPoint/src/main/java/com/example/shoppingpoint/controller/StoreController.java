package com.example.shoppingpoint.controller;

import com.example.shoppingpoint.adapter.GenericProduct;
import com.example.shoppingpoint.adapter.ProductAdapter;
import com.example.shoppingpoint.bean.StoreBean;
import com.example.shoppingpoint.dao.LoyaltyCardDAO;
import com.example.shoppingpoint.dao.ProductDAO;
import com.example.shoppingpoint.dao.ReviewDAO;
import com.example.shoppingpoint.exception.ControllerException;
import com.example.shoppingpoint.exception.DatabaseException;
import com.example.shoppingpoint.model.LoyaltyCard;
import com.example.shoppingpoint.model.Review;
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
        return new LoyaltyCard(0, client, storeName);
    }

    //    Return the average of the reviews (only if they are > 0)
    //    if there aren't any reviews, return 0
    public float getReviewOfProduct(int productId) throws ControllerException {
        List<Review> reviews;
        try {
            reviews = ReviewDAO.getReviewsOfProduct(productId);
        } catch (SQLException e) {
            throw new ControllerException("SQL", e);
        }
        float total = 0f;
        int count = 0;
        for (Review r : reviews) {
            if (r.getValue() == 0) continue;
            count += 1;
            total += r.getValue();
        }
        return (count > 0) ? total / count : 0f;
    }
}
