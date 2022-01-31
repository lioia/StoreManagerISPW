package com.example.shoppingpoint.bean;

import com.example.shoppingpoint.exception.BeanException;

public class LoginBean {
    private String username;
    private String password;

    public LoginBean(String username, String password) throws BeanException {
        setUsername(username);
        setPassword(password);
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String user) throws BeanException {
        if (user.length() < 4) throw new BeanException("username", "it has to be longer than 4 characters");
        if (user.length() > 16) throw new BeanException("username", "it has to be shorter than 16 characters");
        this.username = user;
    }

    public void setPassword(String pass) throws BeanException {
        if (pass.length() < 8) throw new BeanException("password", "it has to be longer than 8 characters");
        if (pass.length() > 16) throw new BeanException("password", "it has to be shorter than 16 characters");
        this.password = pass;
    }
}
