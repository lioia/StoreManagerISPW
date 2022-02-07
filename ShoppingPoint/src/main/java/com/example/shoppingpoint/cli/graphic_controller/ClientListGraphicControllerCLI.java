package com.example.shoppingpoint.cli.graphic_controller;

import com.example.shoppingpoint.cli.view.ClientListViewCLI;
import com.example.shoppingpoint.controller.ClientListController;
import com.example.shoppingpoint.controller.SendEmailController;
import com.example.shoppingpoint.exception.ControllerException;
import com.example.shoppingpoint.exception.EmailException;
import com.example.shoppingpoint.model.ClientListData;
import com.example.shoppingpoint.model.Store;
import com.example.shoppingpoint.model.user.StoreOwner;
import com.example.shoppingpoint.singleton.LoggedInUser;

import java.io.IOException;
import java.util.List;

public class ClientListGraphicControllerCLI {
    public void initialize() throws ControllerException, IOException ,EmailException{
        Store store = ((StoreOwner) LoggedInUser.getInstance().getUser()).getStore();
        ClientListController controller = new ClientListController();
        List<ClientListData> clients = controller.getClientFromStore(store.getName());
        ClientListViewCLI clientListViewCLI = new ClientListViewCLI();
        clientListViewCLI.viewClientList(clients);
        if(clientListViewCLI.getChoice()){
            sendEmail(clients,clientListViewCLI);
        }

    }

    private void sendEmail(List<ClientListData> clients,ClientListViewCLI clientListViewCLI)throws  ControllerException, EmailException, IOException{
        SendEmailController controller = new SendEmailController();
        String client = clientListViewCLI.getClient();
        ClientListData user = clients.stream().filter(el -> el.getUsername().equals(client)).findFirst().orElse(null);
        if(user == null) {
            System.out.println("Invalid input: Client not found");}
        else {
            controller.sendEmail(client);
        }

    }
}
