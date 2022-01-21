package com.example.shoppingpoint.controller;

import com.example.shoppingpoint.bean.OrdersBean;
import com.example.shoppingpoint.dao.ReviewDAO;
import com.example.shoppingpoint.dao.SoldProductDAO;
import com.example.shoppingpoint.model.Review;
import com.example.shoppingpoint.model.SoldProduct;

import java.util.ArrayList;
import java.util.List;

public class OrdersController {
    public List<SoldProduct> getOrders(String client, String storeName) throws Exception {
        List<SoldProduct> products = SoldProductDAO.getProductsOfClient(client);
        List<SoldProduct> filteredProducts = new ArrayList<>();

        for (SoldProduct p : products) {
            if (p.getProduct().getStoreName().equals(storeName)) filteredProducts.add(p);
        }

        return filteredProducts;
    }

    public Review getReview(String client, int productId) throws Exception {
        return ReviewDAO.getReviewFromClientAndProductId(client, productId);
    }

    public void updateReview(OrdersBean bean, int reviewId, String client, int productId) throws Exception {
        ReviewDAO.updateReview(reviewId, bean.getValue(), client, productId);
    }
}
