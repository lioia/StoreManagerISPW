package com.example.shoppingpoint.cli.view;

import com.example.shoppingpoint.bean.RequestListBean;
import com.example.shoppingpoint.cli.utils.CLIReader;
import com.example.shoppingpoint.exception.BeanException;
import com.example.shoppingpoint.model.Request;

import java.io.IOException;
import java.util.List;

public class RequestListViewCli {
    public void requestList(int acceptedOffer, List<Request> requestList) {
        System.out.println("Request list");
        if (acceptedOffer > 0) {
            System.out.printf("They accepted %d offers%n", acceptedOffer);
        }
        for (Request request : requestList) {
            System.out.printf("RequestId: %d%n", request.getRequestId());
            System.out.printf("Product name: %s%n", request.getProduct().getName());
            System.out.printf("Quantity: %d%n", request.getQuantity());
            System.out.printf("Max price: %.2f%n", request.getMaxPrice());
            System.out.printf("Description: %s%n", request.getProduct().getDescription());
            System.out.printf("Store: %s%n%n%n", request.getProduct().getStoreName());
        }
    }

    public int getChoiceSupplier() throws IOException {
        System.out.println("What do you want to do?");
        return CLIReader.multiChoice(List.of("Make an offer", "View accepted offer","Send email to store", "Quit"));
    }

    public int makeAnOfferInput() throws IOException {
        System.out.println("Enter the requestId of the product you want to bid on:");
        String requestId = CLIReader.readline();
        return Integer.parseInt(requestId);
    }

    public RequestListBean priceOfOfferInput() throws IOException, BeanException {
        System.out.println("Choose the price in €:");
        String price = CLIReader.readline();
        return new RequestListBean(price);
    }

    public int getRequestId()throws IOException{
        System.out.println("For which request?");
        System.out.println("Request ID:");
        return Integer.parseInt(CLIReader.readline());
    }
}
