package com.example.shoppingpoint.bean;

import com.example.shoppingpoint.utils.UserType;
import java.util.regex.*;

public class RegisterBean {
    private String username;
    private String email;
    private String password;
    private UserType userType;

    public RegisterBean() {}

    public  RegisterBean(String email, String username, String userType, String password, String verifyPassword) {
        setEmail(email);
        setUsername(username);
        setUserType(userType);
        setPassword(password);
        setVerifyPassword(password,verifyPassword);
    }
    public String getEmail(){return  email;}
    public String getUsername(){ return username;}
    public UserType getUserType(){ return userType;}
    public String getPassword(){ return password;}


    // TODO: bean exception
    public void setUsername(String user) {
        if (user.length() < 4 || user.length() > 16) {
//            TODO throws exception
            System.out.println("Username not valid");
        }
        this.username = user;
    }

    public void setPassword(String pass) {
        if(pass.length() < 8 || pass.length() > 16) {
//            TODO throws exception
            System.out.println("Password not valid");
        }
        this.password = pass;
    }

    public void setVerifyPassword(String pass,String verifyPassword) {
        if(!pass.equals(verifyPassword)) {
//            TODO throws exception
            System.out.println("VerifyPassword not valid");
        }
    }

    public void  setEmail(String email) {
        String regex = "[a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        if(!matcher.matches()){
            //            TODO throws exception
            System.out.println("Email not valid");
        }
        this.email= email;
    }

    public void setUserType(String type){
        switch (type) {
            case "Store Owner" -> this.userType = UserType.STOREOWNER;
            case "Supplier" -> this.userType = UserType.SUPPLIER;
            default -> this.userType = UserType.CLIENT; // case "Client"
        }
    }
}
