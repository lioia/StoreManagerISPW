package com.example.shoppingpoint.singleton;

import com.example.shoppingpoint.model.user.User;

public class LoggedInUser {
    private static LoggedInUser instance = null;

    private User user;

    protected LoggedInUser() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public synchronized static LoggedInUser getInstance() {
        if (LoggedInUser.instance == null)
            LoggedInUser.instance = new LoggedInUser();
        return instance;
    }
}
