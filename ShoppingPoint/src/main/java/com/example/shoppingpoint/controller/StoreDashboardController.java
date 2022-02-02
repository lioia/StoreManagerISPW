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
    public Store getStoreFromStoreOwnerName(String storeOwner) throws Exception {
        return StoreDAO.getStoreByStoreOwnerUsername(storeOwner);
    }

    public List<GenericProduct> getProductsFromStore(Store store) throws SQLException {
        List<Product> products = ProductDAO.getProductsFromStore(store.getName());

        List<GenericProduct> genericProducts = new ArrayList<>();
        for (Product product : products) {
            genericProducts.add(new ProductAdapter(product));
        }

        return genericProducts;
    }

    public void updateLoyaltyCard(LoyaltyCardBean bean, Store store) throws SQLException {
        StoreDAO.updatePoints(bean.getPointsInEuro(), bean.getEuroInPoints(), store.getName());
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

    public void editProduct(Integer id, EditProductBean bean) throws SQLException {
        ProductDAO.updateProduct(id, bean.getPrice(), bean.getDiscountedPrice(), bean.getQuantity());
    }
}
