package com.example.shoppingpoint.cli.graphic_controller;

import com.example.shoppingpoint.bean.OrdersBean;
import com.example.shoppingpoint.cli.view.OrdersViewCLI;
import com.example.shoppingpoint.controller.ReviewController;
import com.example.shoppingpoint.exception.BeanException;
import com.example.shoppingpoint.exception.ControllerException;
import com.example.shoppingpoint.model.Review;
import com.example.shoppingpoint.model.SoldProduct;
import com.example.shoppingpoint.model.Store;
import com.example.shoppingpoint.singleton.LoggedInUser;
import javafx.util.Pair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OrdersGraphicControllerCLI {
    public void initialize(Store store) throws ControllerException, IOException {
        OrdersViewCLI view = new OrdersViewCLI();
        ReviewController controller = new ReviewController();
        List<SoldProduct> orders = controller.getOrders(LoggedInUser.getInstance().getUser().getUsername(), store.getName());
        List<Pair<SoldProduct, Review>> ordersWithReview = new ArrayList<>();
        for (SoldProduct order : orders) {
            Review review = controller.getReview(LoggedInUser.getInstance().getUser().getUsername(), order);
            ordersWithReview.add(new Pair<>(order, review));
        }
        view.showOrdersList(ordersWithReview);
        boolean action = view.selectAction();
        if (action) {
            int id = Integer.parseInt(view.getProduct());
            Pair<SoldProduct, Review> pair = ordersWithReview.stream().filter(el -> el.getValue().getReviewId() == id).findFirst().orElse(null);
            if (pair == null) {
                System.out.println("Invalid input");
                return;
            }
            String newReview = view.getReview();
            try {
                OrdersBean bean = new OrdersBean(Float.parseFloat(newReview));
                controller.updateReview(bean, pair.getValue().getReviewId(), LoggedInUser.getInstance().getUser().getUsername(), pair.getKey());
            } catch (BeanException e) {
                System.out.println("Invalid input");
            }
        }
    }
}
