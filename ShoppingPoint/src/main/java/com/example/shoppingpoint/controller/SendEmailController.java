package com.example.shoppingpoint.controller;

import com.example.shoppingpoint.dao.StoreDAO;
import com.example.shoppingpoint.dao.UserDAO;
import com.example.shoppingpoint.exception.ControllerException;
import com.example.shoppingpoint.exception.DatabaseException;
import com.example.shoppingpoint.exception.EmailException;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
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

    public void sendEmail(String user) throws ControllerException, EmailException {
        String email;
        try {
            email = UserDAO.getEmailByUsername(user);
        } catch (SQLException e) {
            throw new ControllerException("SQL", e);
        } catch (DatabaseException e) {
            throw new ControllerException("Database", e);
        }
        Desktop.getDesktop();
        if (!Desktop.isDesktopSupported()) {
            throw new EmailException("Not supported");
        }
        try {
            Desktop.getDesktop().mail(new URI("mailto:" + email));
        } catch (URISyntaxException e) {
            throw new EmailException("Incorrect email format");
        } catch (IOException e) {
            throw new EmailException("Unable to send email");
        }
    }
}
