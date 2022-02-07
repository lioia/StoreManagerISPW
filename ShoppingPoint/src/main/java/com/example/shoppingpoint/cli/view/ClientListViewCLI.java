package com.example.shoppingpoint.cli.view;

import com.example.shoppingpoint.model.ClientListData;

import java.util.List;

public class ClientListViewCLI {
    public void viewClientList(List<ClientListData> clientList) {
        System.out.println("Client List");
        for (ClientListData client : clientList) {
            System.out.printf("%s - ", client.getUsername());
            System.out.printf("%s | ", client.getEmail());
            System.out.printf("Points : %d\n", client.getPoints());
        }
    }
}
