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
        if (user.length() < 4) throw new BeanException("username", "too short");
        if (user.length() > 16) throw new BeanException("username", "too long");
        this.username = user;
    }

    public void setPassword(String pass) throws BeanException {
        if (pass.length() < 8) throw new BeanException("password", "too short");
        if (pass.length() > 16) throw new BeanException("password", "too long");
        this.password = pass;
    }
}
