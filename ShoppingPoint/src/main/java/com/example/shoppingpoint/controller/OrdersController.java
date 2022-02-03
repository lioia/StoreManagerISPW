package com.example.shoppingpoint.controller;

import com.example.shoppingpoint.bean.OrdersBean;
import com.example.shoppingpoint.dao.ReviewDAO;
import com.example.shoppingpoint.dao.SoldProductDAO;
import com.example.shoppingpoint.exception.ControllerException;
import com.example.shoppingpoint.exception.DatabaseException;
import com.example.shoppingpoint.model.Review;
import com.example.shoppingpoint.model.SoldProduct;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrdersController {
    public List<SoldProduct> getOrders(String client, String storeName) throws ControllerException {
        List<SoldProduct> products = null;
        try {
            products = SoldProductDAO.getProductsOfClient(client);

            List<SoldProduct> filteredProducts = new ArrayList<>();

            for (SoldProduct p : products) {
                if (p.getProduct().getStoreName().equals(storeName)) filteredProducts.add(p);
            }

            return filteredProducts;
        } catch (SQLException e) {
            throw new ControllerException("SQL", e);
        } catch (DatabaseException e) {
            throw new ControllerException("Database", e);
        }
    }

    public Review getReview(String client, int productId) throws ControllerException {
        try {
            return ReviewDAO.getReviewFromClientAndProductId(client, productId);
        } catch (SQLException e) {
            throw new ControllerException("SQL", e);
        } catch (DatabaseException e) {
            throw new ControllerException("Database", e);
        }
    }

    public void updateReview(OrdersBean bean, int reviewId, String client, int productId) throws ControllerException {
        try {
            ReviewDAO.updateReview(reviewId, bean.getValue(), client, productId);
        } catch (SQLException e) {
            throw new ControllerException("SQL", e);
        }
    }
}
