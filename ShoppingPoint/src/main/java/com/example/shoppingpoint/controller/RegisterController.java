package com.example.shoppingpoint.controller;

import com.example.shoppingpoint.bean.RegisterBean;
import com.example.shoppingpoint.dao.UserDAO;
import com.example.shoppingpoint.model.user.User;

public class RegisterController {
    public User register(RegisterBean bean) throws Exception {
        UserDAO.saveUser(bean.getUsername(), bean.getEmail(), bean.getPassword(), bean.getUserType());
        return UserDAO.getUserByUsername(bean.getUsername());
    }
}
