package com.shoppingpoint.cli.graphic_controller;

import com.shoppingpoint.bean.OrdersBean;
import com.shoppingpoint.cli.view.OrdersViewCLI;
import com.shoppingpoint.controller.ReviewController;
import com.shoppingpoint.exception.BeanException;
import com.shoppingpoint.exception.ControllerException;
import com.shoppingpoint.model.Review;
import com.shoppingpoint.model.SoldProduct;
import com.shoppingpoint.model.Store;
import com.shoppingpoint.singleton.LoggedInUser;
import javafx.util.Pair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OrdersGraphicControllerCLI {
    public void initialize(Store store) throws IOException {
        OrdersViewCLI view = new OrdersViewCLI();
        ReviewController controller = new ReviewController();
        try {
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
                updateReview(pair, view.getReview());
            }
        } catch (ControllerException e) {
            e.printStackTrace();
        }
    }

    private void updateReview(Pair<SoldProduct, Review> pair, String newReview) {
        try {
            OrdersBean bean = new OrdersBean(Float.parseFloat(newReview));
            ReviewController controller = new ReviewController();
            controller.updateReview(bean, pair.getValue().getReviewId(), LoggedInUser.getInstance().getUser().getUsername(), pair.getKey());
        } catch (BeanException e) {
            System.out.println("Invalid input");
        } catch (ControllerException e) {
            System.out.println("[ERR] " + e.getMessage());
        }
    }
}
