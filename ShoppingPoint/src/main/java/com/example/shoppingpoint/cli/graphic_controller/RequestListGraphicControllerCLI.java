package com.example.shoppingpoint.cli.graphic_controller;

import com.example.shoppingpoint.cli.view.RequestListViewCli;
import com.example.shoppingpoint.exception.BeanException;

import java.io.IOException;

public class RequestListGraphicControllerCLI {
    private final RequestListViewCli requestListView;

    public RequestListGraphicControllerCLI() {
        requestListView = new RequestListViewCli();
    }

    public void initialize() throws IOException {
        try {
            requestListView.requestList();
        } catch (BeanException e) {
            System.out.println(e.getMessage());
        }
    }
}
