package com.example.shoppingpoint.bean;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EditAccountBean {
    private String email;
    private String password;
    private String verifyPassword;

    public EditAccountBean(){}

    public EditAccountBean(String email,String password, String verifyPassword){
        setEmail(email);
        setPassword(password);
        setVerifyPassword(password,verifyPassword);
    }

    public void setEmail(String email) {
        String regex = "[a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        if(!matcher.matches()){
            //            TODO throws exception
            System.out.println("Email not valid");
        }
        this.email = email;
    }

    public void setPassword(String password) {
        if(password.length() < 8 || password.length() > 16) {
//            TODO throws exception
            System.out.println("Password not valid");
        }
        this.password = password;
    }

    public void setVerifyPassword(String password,String verifyPassword) {
        if(!password.equals(verifyPassword)) {
//            TODO throws exception
            System.out.println("VerifyPassword not valid");
        }
    }
}
