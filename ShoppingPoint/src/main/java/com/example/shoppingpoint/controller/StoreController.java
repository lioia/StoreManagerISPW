package com.example.shoppingpoint.controller;

import com.example.shoppingpoint.adapter.GenericProduct;
import com.example.shoppingpoint.adapter.ProductAdapter;
import com.example.shoppingpoint.bean.StoreBean;
import com.example.shoppingpoint.dao.LoyaltyCardDAO;
import com.example.shoppingpoint.dao.ProductDAO;
import com.example.shoppingpoint.dao.ReviewDAO;
import com.example.shoppingpoint.exception.DatabaseException;
import com.example.shoppingpoint.model.LoyaltyCard;
import com.example.shoppingpoint.model.Review;
import com.example.shoppingpoint.model.product.Product;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StoreController {
    public List<GenericProduct> getProductsFromStore(StoreBean bean) throws SQLException {
        List<Product> products = ProductDAO.getProductsFromStore(bean.getStoreName());
        List<Product> filteredProducts = new ArrayList<>();
        if (bean.getSearchQuery().isEmpty()) filteredProducts = products;
        else {
            for (Product product : products) {
                if (product.getName().toLowerCase().contains(bean.getSearchQuery().toLowerCase()))
                    filteredProducts.add(product);
            }
        }

        List<GenericProduct> genericProducts = new ArrayList<>();
        for (Product product : filteredProducts) {
            genericProducts.add(new ProductAdapter(product));
        }

        return genericProducts;
    }

    public LoyaltyCard getLoyaltyCard(String client, String storeName) throws SQLException, DatabaseException {
        return LoyaltyCardDAO.getLoyaltyCardFromClientAndStoreName(client, storeName);
    }

    public LoyaltyCard createLoyaltyCard(String client, String storeName) throws SQLException {
        LoyaltyCardDAO.saveLoyaltyCard(client, storeName, 0);
        return new LoyaltyCard(0, client, storeName);
    }

    //    Return the average of the reviews (only if they are > 0)
    //    if there aren't any reviews, return 0
    public float getReviewOfProduct(int productId) throws SQLException {
        List<Review> reviews = ReviewDAO.getReviewsOfProduct(productId);
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
