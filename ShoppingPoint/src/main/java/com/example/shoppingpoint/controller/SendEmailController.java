package com.example.shoppingpoint.controller;

import com.example.shoppingpoint.ShoppingPointApplication;
import com.example.shoppingpoint.dao.StoreDAO;
import com.example.shoppingpoint.dao.UserDAO;
import com.example.shoppingpoint.exception.ControllerException;
import com.example.shoppingpoint.exception.DatabaseException;
import com.example.shoppingpoint.singleton.LoggedInUser;
import javafx.application.HostServices;

import java.sql.SQLException;

public class SendEmailController {
    public String getUsernameOfStore(String storeName) throws ControllerException {
        try {
            return StoreDAO.getStoreOwnerUsernameByStoreName(storeName);
        } catch (SQLException e) {
            throw new ControllerException("SQL", e);
        } catch (DatabaseException e) {
            throw new ControllerException("Database", e);
        }
    }

    public void sendEmail(String user) throws ControllerException {
        String email;
        try {
            email = UserDAO.getEmailByUsername(user);
        } catch (SQLException e) {
            throw new ControllerException("SQL", e);
        } catch (DatabaseException e) {
            throw new ControllerException("Database", e);
        }
        HostServices hostServices = ShoppingPointApplication.getInstance().getHostServices();

        hostServices.showDocument("mailto:" + email + "?Subject=[Shopping Point] You received an email from " + LoggedInUser.getInstance().getUser().getUsername());
    }
}
