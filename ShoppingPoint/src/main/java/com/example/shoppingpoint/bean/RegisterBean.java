package com.example.shoppingpoint.bean;

import com.example.shoppingpoint.exception.BeanException;
import com.example.shoppingpoint.utils.UserType;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterBean {
    private String username;
    private String email;
    private String password;
    private UserType userType;

    public RegisterBean(String email, String username, String userType, String password, String verifyPassword) throws BeanException {
        setEmail(email);
        setRegisterUsername(username);
        setUserType(userType);
        setRegisterPassword(password);
        setVerifyPassword(password, verifyPassword);
    }

    public String getEmail() {
        return email;
    }

    public String getRegisterUsername() {
        return username;
    }

    public UserType getUserType() {
        return userType;
    }

    public String getRegisterPassword() {
        return password;
    }

    public void setRegisterUsername(String user) throws BeanException {
        if (user.length() < 4) throw new BeanException("register username", BeanException.TOO_SHORT_REASON);
        if (user.length() > 16) throw new BeanException("register username", BeanException.TOO_LONG_REASON);
        this.username = user;
    }

    public void setRegisterPassword(String pass) throws BeanException {
        if (pass.length() < 8) throw new BeanException("register password", BeanException.TOO_SHORT_REASON);
        if (pass.length() > 16) throw new BeanException("register password", BeanException.TOO_LONG_REASON);
        this.password = pass;
    }

    public void setVerifyPassword(String pass, String verifyPassword) throws BeanException {
        if (!pass.equals(verifyPassword)) throw new BeanException("register verify password", "not equal to password");
    }

    public void setEmail(String email) throws BeanException {
        String regex = "[a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches())
            throw new BeanException("register email", "not valid");

        this.email = email;
    }

    public void setUserType(String type) {
        switch (type) {
            case "Client" -> this.userType = UserType.CLIENT;
            case "Store Owner" -> this.userType = UserType.STOREOWNER;
            case "Supplier" -> this.userType = UserType.SUPPLIER;
            default -> throw new IllegalStateException("Unexpected value: " + type);
        }
    }
}
