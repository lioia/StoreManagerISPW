package com.example.shoppingpoint.controller;

import com.example.shoppingpoint.bean.LoginBean;
import com.example.shoppingpoint.dao.UserDAO;
import com.example.shoppingpoint.model.user.User;

public class LoginController {
    public User login(LoginBean bean) throws Exception {
        return UserDAO.getUserByUsernameAndPasssword(bean.getUsername(), bean.getUsername());
    }
}
