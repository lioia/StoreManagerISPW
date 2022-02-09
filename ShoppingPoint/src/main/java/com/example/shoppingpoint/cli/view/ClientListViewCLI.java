package com.example.shoppingpoint.cli.view;

import com.example.shoppingpoint.cli.utils.CLIReader;
import com.example.shoppingpoint.model.ClientListData;

import java.io.IOException;
import java.util.List;

public class ClientListViewCLI {
    public void viewClientList(List<ClientListData> clientList) {
        System.out.println("--------------------");
        System.out.println("Client List");
        for (ClientListData client : clientList) {
            System.out.printf("%s - ", client.getUsername());
            System.out.printf("%s | ", client.getEmail());
            System.out.printf("Points : %d\n", client.getPoints());
        }
    }

    public boolean getChoice() throws IOException {
        return CLIReader.yesOrNo("Do you want to send an email to a customer?? [y/n]");
    }

    public String getClient() throws IOException {
        System.out.println("Client username:");
        return CLIReader.readline();
    }
}
