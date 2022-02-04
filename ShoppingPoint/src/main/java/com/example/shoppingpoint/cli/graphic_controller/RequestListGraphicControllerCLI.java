package com.example.shoppingpoint.cli.graphic_controller;

import com.example.shoppingpoint.cli.view.RequestListViewCli;
import com.example.shoppingpoint.controller.MakeOfferController;
import com.example.shoppingpoint.exception.BeanException;
import com.example.shoppingpoint.exception.ControllerException;

import java.io.IOException;
import java.util.List;
import com.example.shoppingpoint.model.Request;

public class RequestListGraphicControllerCLI {
    private final RequestListViewCli requestListView;
    public RequestListGraphicControllerCLI() {
        requestListView = new RequestListViewCli();
    }

    public void initialize()throws BeanException, IOException, ControllerException {


        MakeOfferController controller = new MakeOfferController();
        int acceptedOffers = controller.countAcceptedOffers();
        List<Request> requestList = controller.getRequest();
        requestListView.requestList(acceptedOffers,requestList);
        int choice = requestListView.getChoiceSupplier();
        System.out.println(choice);

    }
}
