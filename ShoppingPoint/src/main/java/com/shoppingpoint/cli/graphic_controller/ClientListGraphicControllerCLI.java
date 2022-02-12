package com.shoppingpoint.cli.graphic_controller;

import com.shoppingpoint.cli.view.ClientListViewCLI;
import com.shoppingpoint.controller.ClientListController;
import com.shoppingpoint.controller.SendEmailController;
import com.shoppingpoint.exception.ControllerException;
import com.shoppingpoint.exception.EmailException;
import com.shoppingpoint.model.ClientListData;
import com.shoppingpoint.model.Store;
import com.shoppingpoint.model.user.StoreOwner;
import com.shoppingpoint.singleton.LoggedInUser;

import java.io.IOException;
import java.util.List;

public class ClientListGraphicControllerCLI {
    public void initialize() throws IOException {
        Store store = ((StoreOwner) LoggedInUser.getInstance().getUser()).getStore();
        ClientListController controller = new ClientListController();
        try {
            List<ClientListData> clients = controller.getClientFromStore(store.getName());
            ClientListViewCLI clientListViewCLI = new ClientListViewCLI();
            clientListViewCLI.viewClientList(clients);
            if (clientListViewCLI.getChoice()) {
                sendEmail(clients, clientListViewCLI);
            }
        } catch (ControllerException e) {
            System.out.println("[ERR] " + e.getMessage());
        }
    }

    private void sendEmail(List<ClientListData> clients, ClientListViewCLI clientListViewCLI) throws IOException {
        SendEmailController controller = new SendEmailController();
        String client = clientListViewCLI.getClient();
        try {
            ClientListData user = clients.stream().filter(el -> el.getUsername().equals(client)).findFirst().orElse(null);
            if (user == null) {
                System.out.println("Invalid input: Client not found");
            } else {
                controller.sendEmail(client);
            }
        } catch (ControllerException e) {
            System.out.println("[EMAIL] " + e.getMessage());
        } catch (EmailException e) {
            System.out.println("[ERR] " + e.getMessage());
        }
    }
}
