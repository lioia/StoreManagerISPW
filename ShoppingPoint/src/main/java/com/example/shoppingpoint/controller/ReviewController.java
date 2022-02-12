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

public class ReviewController {
    public List<SoldProduct> getOrders(String client, String storeName) throws ControllerException {
        List<SoldProduct> products;
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

    public Review getReview(String client, SoldProduct soldProduct) throws ControllerException {
        try {
            return ReviewDAO.getReviewFromClientAndSoldProductId(client, soldProduct.getSoldProductId());
        } catch (SQLException e) {
            throw new ControllerException("SQL", e);
        } catch (DatabaseException e) {
            throw new ControllerException("Database", e);
        }
    }

    public void updateReview(OrdersBean bean, int reviewId, String client, SoldProduct soldProduct) throws ControllerException {
        try {
            ReviewDAO.updateReview(reviewId, bean.getValue(), client, soldProduct.getSoldProductId());
        } catch (SQLException e) {
            throw new ControllerException("SQL", e);
        }
    }

    //    Return the average of the reviews (only if they are > 0)
    //    if there aren't any reviews, return 0
    public float getAverageReviewOfProduct(int productId) throws ControllerException {
        List<Review> reviews;
        try {
            reviews = ReviewDAO.getReviewsOfProduct(productId);
        } catch (SQLException e) {
            throw new ControllerException("SQL", e);
        } catch (DatabaseException e) {
            throw new ControllerException("Product", e);
        }
        List<Float> reviewsValue = new ArrayList<>();
        for (Review r : reviews) {
            reviewsValue.add(r.getValue());
        }

        return calculateAverage(reviewsValue);
    }

    public float calculateAverage(List<Float> reviews) {
        float total = 0f;
        int count = 0;
        for (float r : reviews) {
            if (r == 0) continue;
            count += 1;
            total += r;
        }
        return (count > 0) ? total / count : 0f;
    }
}
