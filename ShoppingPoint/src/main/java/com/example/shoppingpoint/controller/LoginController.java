package com.example.shoppingpoint.controller;

import com.example.shoppingpoint.bean.LoginBean;
import com.example.shoppingpoint.dao.UserDAO;
import com.example.shoppingpoint.model.user.User;

import java.util.Objects;

public class LoginController {
    public User login(LoginBean bean) throws Exception {
        User user = UserDAO.getUserByUsername(bean.getUsername());
        if(!Objects.equals(user.getPassword(), bean.getPassword())) {
            throw new Exception("");
        }
        return user;
    }
}
