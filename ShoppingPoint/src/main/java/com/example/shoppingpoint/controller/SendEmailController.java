package com.example.shoppingpoint.controller;

import com.example.shoppingpoint.ShoppingPointApplication;
import com.example.shoppingpoint.singleton.LoggedInUser;
import javafx.application.HostServices;

public class SendEmailController {
    public void sendEmail(String recipient){
        HostServices hostServices = ShoppingPointApplication.getInstance().getHostServices();

        hostServices.showDocument("mailto:" + recipient + "?Subject=[Shopping Point] You received an email from " + LoggedInUser.getInstance().getUser().getUsername() );
    }
}
