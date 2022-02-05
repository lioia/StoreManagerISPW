package com.example.shoppingpoint.cli.view;
import com.example.shoppingpoint.adapter.GenericProduct;
import com.example.shoppingpoint.adapter.ProductAdapter;
import com.example.shoppingpoint.bean.RequestListBean;
import com.example.shoppingpoint.cli.utils.CLIReader;
import com.example.shoppingpoint.controller.MakeOfferController;
import com.example.shoppingpoint.exception.BeanException;
import com.example.shoppingpoint.exception.ControllerException;
import com.example.shoppingpoint.model.Offer;
import com.example.shoppingpoint.model.Request;
import java.util.List;


import java.io.IOException;

public class RequestListViewCli {
    public void requestList(int acceptedOffer, List<Request> requestList) throws  ControllerException {
        System.out.println("Request list");
        if(acceptedOffer>0){System.out.printf("They accepted %d offers\n",acceptedOffer);}
        //TODO per prendere il prodotto come gestisco il controller?
        MakeOfferController controller = new MakeOfferController();
        for (Request request : requestList) {

            GenericProduct product = new ProductAdapter(controller.getProduct(request.getProductId()));
                System.out.printf("RequestId: %d\n",request.getRequestId());
                System.out.printf("Product name: %s\n",product.getName());
                System.out.printf("Quantity: %d\n",request.getQuantity());
                System.out.printf("Max price: %.2f\n",request.getMaxPrice());
                System.out.printf("Description: %s\n",product.getDescription());
                System.out.printf("Store: %s\n\n\n",product.getStoreName());
        }

    }
    public int getChoiceSupplier()throws IOException {
        System.out.println("What do you want to do?");
        System.out.println("1) Make an offer");
        System.out.println("2) View accepted offer");
        System.out.println("3) Quit");
        boolean exit = false;
        int selected=0;
        while(!exit) {
            String option = CLIReader.readline();
            selected = Integer.parseInt(option);
            if(selected == 1) {
                System.out.println("Request Id:");

                exit = true;
            } else if (selected == 2) {
                System.out.println("Accepted offers");

                exit = true;
            }else if (selected == 3) {
                System.out.println("Quit");

                exit = true;}
            else {
                System.out.println("Invalid input.");
            }

        }
        return selected;
    }

    public int makeAnOfferInput() throws IOException{
        System.out.println("Enter the requestId of the product you want to bid on:");
        String requestId = CLIReader.readline();
        //TODO contorllo input

        return  Integer.parseInt(requestId);

    }

    public RequestListBean priceOfOfferInput() throws IOException,BeanException{
        System.out.println("Choose the price in €:");
        String price = CLIReader.readline();
        return  new RequestListBean(price);

    }





}
