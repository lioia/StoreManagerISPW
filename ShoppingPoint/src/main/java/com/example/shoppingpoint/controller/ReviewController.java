package com.example.shoppingpoint.controller;

import com.example.shoppingpoint.dao.ReviewDAO;
import com.example.shoppingpoint.exception.ControllerException;
import com.example.shoppingpoint.model.Review;

import java.sql.SQLException;
import java.util.List;

public class ReviewController {
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
