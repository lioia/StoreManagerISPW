package com.example.shoppingpoint.cli.graphic_controller;

import com.example.shoppingpoint.cli.view.RatingProductViewCLI;
import com.example.shoppingpoint.controller.ReviewController;
import com.example.shoppingpoint.exception.ControllerException;

import java.io.IOException;
//TODO cancella
public class RatingProductGraphicControllerCLI {
    public void initialize()throws IOException, ControllerException {
        RatingProductViewCLI ratingProductViewCLI = new RatingProductViewCLI();
        int productId = ratingProductViewCLI.getProductId();
        ReviewController reviewController = new ReviewController();
        //float reviewAverage = reviewController.getReviewOfProduct(productId);
        //ratingProductViewCLI.viewRating(reviewAverage);
    }
}
