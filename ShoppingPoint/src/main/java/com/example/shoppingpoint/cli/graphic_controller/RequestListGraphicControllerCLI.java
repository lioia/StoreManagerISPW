package com.example.shoppingpoint.cli.graphic_controller;

import com.example.shoppingpoint.bean.RequestListBean;
import com.example.shoppingpoint.cli.view.RequestListViewCli;
import com.example.shoppingpoint.controller.MakeOfferController;
import com.example.shoppingpoint.exception.BeanException;
import com.example.shoppingpoint.exception.ControllerException;
import com.example.shoppingpoint.model.Request;

import java.io.IOException;
import java.util.List;

public class RequestListGraphicControllerCLI {
    private final RequestListViewCli requestListView;
    public RequestListGraphicControllerCLI() {
        requestListView = new RequestListViewCli();
    }

    public void initialize()throws BeanException, IOException, ControllerException {


        MakeOfferController controller = new MakeOfferController();
        int nAcceptedOffers = controller.countAcceptedOffers();
        List<Request> requestList = controller.getRequest();
        requestListView.requestList(nAcceptedOffers,requestList);
        int choice=0;
        while(choice!=3) {
            choice=requestListView.getChoiceSupplier();
            if (choice == 1) {
                int requestId = requestListView.makeAnOfferInput();
                RequestListBean price = requestListView.priceOfOfferInput();
                controller.saveOffer(requestId, price);
            }
            if(choice==2) {
                AcceptedOfferGraphicControllerCLI acceptedOfferGraphicControllerCLI = new AcceptedOfferGraphicControllerCLI();
                acceptedOfferGraphicControllerCLI.initialize();

            }
        }

    }
}
