package com.example.shoppingpoint.cli.graphic_controller;

import com.example.shoppingpoint.cli.view.RequestListViewCli;
import com.example.shoppingpoint.exception.BeanException;

import java.io.IOException;

public class RequestListGraphicControllerCLI {
    private RequestListViewCli requestListView;
    public RequestListGraphicControllerCLI(){ requestListView = new RequestListViewCli();}

    public void initialize()throws BeanException, IOException {
        requestListView.requestList();

    }
}
