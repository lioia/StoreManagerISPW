package com.shoppingpoint.controller;

import com.shoppingpoint.dao.StoreDAO;
import com.shoppingpoint.dao.UserDAO;
import com.shoppingpoint.exception.ControllerException;
import com.shoppingpoint.exception.DatabaseException;
import com.shoppingpoint.exception.EmailException;
import com.shoppingpoint.singleton.LoggedInUser;

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
            URI mailto = new URI("mailto:" + email + "?subject=[Shopping%20Point]%20You%20received%20an%20email%20from%20" + LoggedInUser.getInstance().getUser().getUsername());
            Desktop.getDesktop().mail(mailto);
        } catch (URISyntaxException e) {
            throw new EmailException("Incorrect email format");
        } catch (IOException e) {
            throw new EmailException("Unable to send email");
        }
    }
}
