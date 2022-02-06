package com.example.shoppingpoint.cli.graphic_controller;

import com.example.shoppingpoint.cli.view.ClientListViewCLI;
import com.example.shoppingpoint.controller.ClientListController;
import com.example.shoppingpoint.exception.ControllerException;
import com.example.shoppingpoint.model.ClientListData;
import com.example.shoppingpoint.model.Store;
import com.example.shoppingpoint.model.user.StoreOwner;
import com.example.shoppingpoint.singleton.LoggedInUser;

import java.util.List;

public class ClientListGraphicControllerCLI {
    public void initialize() throws ControllerException {
        Store store = ((StoreOwner) LoggedInUser.getInstance().getUser()).getStore();
        ClientListController controller = new ClientListController();
        List<ClientListData> clients = controller.getClientFromStore(store.getName());
        ClientListViewCLI clientListViewCLI = new ClientListViewCLI();
        clientListViewCLI.viewClientList(clients);
    }
}
