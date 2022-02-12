package com.shoppingpoint.cli.graphic_controller;

import com.shoppingpoint.bean.RequestListBean;
import com.shoppingpoint.cli.view.RequestListViewCli;
import com.shoppingpoint.controller.MakeOfferController;
import com.shoppingpoint.controller.SendEmailController;
import com.shoppingpoint.exception.BeanException;
import com.shoppingpoint.exception.ControllerException;
import com.shoppingpoint.exception.EmailException;
import com.shoppingpoint.model.Request;

import java.io.IOException;
import java.util.List;

public class RequestListGraphicControllerCLI {
    private final RequestListViewCli requestListView;

    public RequestListGraphicControllerCLI() {
        requestListView = new RequestListViewCli();
    }

    public void initialize() throws IOException {
        MakeOfferController controller = new MakeOfferController();
        try {
            int nAcceptedOffers = controller.countAcceptedOffers();
            List<Request> requestList = controller.getRequest();
            requestListView.requestList(nAcceptedOffers, requestList);
            boolean exit = false;
            int choice;
            while (!exit) {
                choice = requestListView.getChoiceSupplier();
                switch (choice) {
                    case 1 -> {
                        int requestId = requestListView.makeAnOfferInput();
                        Request request = requestList.stream().filter(el -> el.getRequestId() == requestId).findFirst().orElse(null);
                        if (request == null) {
                            System.out.println("Invalid input");
                            continue;
                        }
                        try {
                            RequestListBean price = requestListView.priceOfOfferInput();
                            controller.saveOffer(request.getRequestId(), price);
                        } catch (BeanException e) {
                            System.out.println("Invalid input: " + e.getMessage());
                        }
                    }
                    case 2 -> {
                        AcceptedOfferGraphicControllerCLI acceptedOfferGraphicControllerCLI = new AcceptedOfferGraphicControllerCLI();
                        acceptedOfferGraphicControllerCLI.initialize();
                    }
                    case 3 -> {
                        int requestId = requestListView.getRequestId();
                        Request request = requestList.stream().filter(el -> el.getRequestId() == requestId).findFirst().orElse(null);
                        if (request == null) {
                            System.out.println("Invalid input: request not found");
                            continue;
                        }
                        SendEmailController sendEmailController = new SendEmailController();
                        String storeOwner = sendEmailController.getUsernameOfStore(request.getProduct().getStoreName());
                        try {
                            sendEmailController.sendEmail(storeOwner);
                        } catch (EmailException e) {
                            System.out.println("[EMAIL] " + e.getMessage());
                        }
                    }
                    default -> exit = true;
                }
            }
        } catch (ControllerException e) {
            System.out.println("[ERR] " + e.getMessage());
        }
    }
}
