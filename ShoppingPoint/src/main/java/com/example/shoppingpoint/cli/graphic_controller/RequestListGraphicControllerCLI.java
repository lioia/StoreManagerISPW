package com.example.shoppingpoint.cli.graphic_controller;

import com.example.shoppingpoint.bean.RequestListBean;
import com.example.shoppingpoint.cli.view.RequestListViewCli;
import com.example.shoppingpoint.controller.MakeOfferController;
import com.example.shoppingpoint.controller.SendEmailController;
import com.example.shoppingpoint.exception.BeanException;
import com.example.shoppingpoint.exception.ControllerException;
import com.example.shoppingpoint.exception.EmailException;
import com.example.shoppingpoint.model.Request;

import java.io.IOException;
import java.util.List;

public class RequestListGraphicControllerCLI {
    private final RequestListViewCli requestListView;

    public RequestListGraphicControllerCLI() {
        requestListView = new RequestListViewCli();
    }

    public void initialize() throws EmailException,BeanException, IOException, ControllerException {
        MakeOfferController controller = new MakeOfferController();
        int nAcceptedOffers = controller.countAcceptedOffers();
        List<Request> requestList = controller.getRequest();
        requestListView.requestList(nAcceptedOffers, requestList);
        boolean exit=false;
        int choice;
        while (!exit) {
            choice = requestListView.getChoiceSupplier();
            switch (choice){
                case 1 -> {
                    int requestId = requestListView.makeAnOfferInput();
                    Request request = requestList.stream().filter(el -> el.getRequestId() == requestId).findFirst().orElse(null);
                    if (request == null) {
                        System.out.println("Invalid input");
                        continue;
                    }
                    RequestListBean price = requestListView.priceOfOfferInput();
                    controller.saveOffer(request.getRequestId(), price);
                }
                case 2 -> {
                    AcceptedOfferGraphicControllerCLI acceptedOfferGraphicControllerCLI = new AcceptedOfferGraphicControllerCLI();
                    acceptedOfferGraphicControllerCLI.initialize();
                }
                case 3 -> {
                    int requestId = requestListView.getRequestId();
                    Request request = requestList.stream().filter(el -> el.getRequestId()==requestId).findFirst().orElse(null);
                    if (request==null){
                        System.out.println("Invalid input: request not found");
                        continue;
                    }
                    SendEmailController sendEmailController = new SendEmailController();
                    String storeOwner = sendEmailController.getUsernameOfStore(request.getProduct().getStoreName());
                    sendEmailController.sendEmail(storeOwner);
                }
                default -> exit=true;
            }


        }
    }
}
