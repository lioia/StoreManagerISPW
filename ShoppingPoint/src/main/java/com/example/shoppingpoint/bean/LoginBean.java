package com.example.shoppingpoint.bean;

public class LoginBean {
    private String username;
    private String password;

    public LoginBean() {}

    public LoginBean(String username, String password) {
        setUsername(username);
        setPassword(password);
    }

    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }

    // TODO: bean exception
    public void setUsername(String user) {
        if (user.length() < 4 || user.length() > 16) {
//            TODO throws exception
            System.out.println("Username check failed");
        }
        this.username = user;
    }

    public void setPassword(String pass) {
        if(pass.length() < 8 || pass.length() > 16) {
//            TODO throws exception
            System.out.println("Password check failed");
        }
        this.password = pass;
    }
}
