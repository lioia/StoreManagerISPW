package com.example.shoppingpoint.cli.graphic_controller;

import com.example.shoppingpoint.cli.view.RequestListViewCli;
import com.example.shoppingpoint.controller.OffersController;
import com.example.shoppingpoint.controller.RequestListController;
import com.example.shoppingpoint.exception.BeanException;
import com.example.shoppingpoint.exception.ControllerException;

import java.io.IOException;

public class RequestListGraphicControllerCLI {
    private final RequestListViewCli requestListView;
    public RequestListGraphicControllerCLI() {
        requestListView = new RequestListViewCli();
    }

    public void initialize()throws BeanException, IOException, ControllerException {
        requestListView.requestList();

        OffersController controller = new OffersController();
        int acceptedOffers = controller.countAcceptedOffers();

    }
}
